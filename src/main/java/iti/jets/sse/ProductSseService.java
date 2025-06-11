package iti.jets.sse;

import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.Product;
import iti.jets.model.mappers.ProductMapper;
import iti.jets.repositories.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class ProductSseService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final Set<SseEmitter> emitters = new CopyOnWriteArraySet<>();

    public ProductSseService(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(e -> emitters.remove(emitter));
        return emitter;
    }

    public void sendNewProduct(ProductDetailDTO product) {
        Product p = productRepo.findById(product.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        ProductSummaryDTO summary = productMapper.entityToSummaryDto(p);
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("new-product")
                        .data(summary));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
