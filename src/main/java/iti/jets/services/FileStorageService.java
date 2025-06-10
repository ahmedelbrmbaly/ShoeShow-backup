package iti.jets.services;

import iti.jets.exceptions.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService(@Value("${app.file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
            log.info("Created file storage directory: {}", this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public List<String> saveImages(List<MultipartFile> images, Long productId) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        Path productPath = this.fileStorageLocation.resolve("product-" + productId);

        // Create product-specific directory
        if (!Files.exists(productPath)) {
            try {
                Files.createDirectories(productPath);
                log.debug("Created product directory: {}", productPath.toAbsolutePath());
            } catch (IOException e) {
                throw new FileStorageException("Cannot create product directory: " + productPath, e);
            }
        }

        if (images == null || images.isEmpty()) {
            log.warn("No images provided for product ID: {}", productId);
            return imagePaths;
        }

        for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            if (image != null && !image.isEmpty()) {
                // Validate image type
                if (!image.getContentType().startsWith("image/")) {
                    log.error("Invalid file type for image: {}", image.getOriginalFilename());
                    throw new IllegalArgumentException("Invalid file type: " + image.getOriginalFilename());
                }

                String fileExtension = getFileExtension(image.getOriginalFilename());
                String fileName = String.format("item%d-%d%s", productId, i + 1, fileExtension);
                Path filePath = productPath.resolve(fileName);

                try {
                    Files.write(filePath, image.getBytes());
                    String relativePath = String.format("uploads/product-%d/%s", productId, fileName);
                    imagePaths.add(relativePath);
                    log.debug("Saved image: {} at {}", relativePath, filePath.toAbsolutePath());
                } catch (IOException e) {
                    throw new FileStorageException("Cannot save image: " + fileName, e);
                }
            } else {
                log.warn("Empty or null image at index {} for product ID: {}", i, productId);
            }
        }

        return imagePaths;
    }

    public void deleteProductImages(Long id) {
        Path productDir = this.fileStorageLocation.resolve("product-" + id);

        try {
            if (Files.exists(productDir)) {
                Files.walk(productDir)
                        .sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                                log.debug("Deleted: {}", path);
                            } catch (IOException e) {
                                log.error("Error deleting {}: {}", path, e.getMessage());
                                throw new FileStorageException("Could not delete " + path, e);
                            }
                        });
            }
        } catch (IOException e) {
            throw new FileStorageException("Could not delete product images for ID: " + id, e);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null) return "";
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot > 0) ? fileName.substring(lastDot) : "";
    }
}

