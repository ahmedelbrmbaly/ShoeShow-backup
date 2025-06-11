package iti.jets.controllers.sse;

import iti.jets.sse.ProductSseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/products/stream")
public class ProductSseController {
    private final ProductSseService productSseService;

    public ProductSseController(ProductSseService productSseService) {
        this.productSseService = productSseService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamNewProducts() {
        return productSseService.subscribe();
    }
}

