package iti.jets.services;

import iti.jets.exceptions.FileStorageException;
import lombok.extern.slf4j.Slf4j;
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
public class FileStorageService
{
    private static final String BASE_STATIC_DIR = "src/main/resources/static/images/";

    public FileStorageService()
    {
        // Ensure base static/images directory exists
        Path basePath = Paths.get(BASE_STATIC_DIR);
        try {
            if (!Files.exists(basePath))
            {
                Files.createDirectories(basePath);
                log.debug("Created base static directory: {}", basePath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new FileStorageException("Cannot initialize static directory: " + basePath, e);
        }
    }

    public List<String> saveImages(List<MultipartFile> images, Long productId) throws IOException 
    {
        List<String> imagePaths = new ArrayList<>();
        Path uploadPath = Paths.get(BASE_STATIC_DIR, "product-" + productId);

        // Create product-specific directory
        if (!Files.exists(uploadPath))
        {
            try {
                Files.createDirectories(uploadPath);
                log.debug("Created product directory: {}", uploadPath.toAbsolutePath());
            } catch (IOException e) {
                throw new FileStorageException("Cannot create product directory: " + uploadPath, e);
            }
        }

        if (images == null || images.isEmpty()) 
        {
            log.warn("No images provided for product ID: {}", productId);
            return imagePaths;
        }

        for (int i = 0; i < images.size(); i++) 
        {
            MultipartFile image = images.get(i);
            if (image != null && !image.isEmpty()) 
            {
                // Validate image type
                if (!image.getContentType().startsWith("image/")) 
                {
                    log.error("Invalid file type for image: {}", image.getOriginalFilename());
                    throw new IllegalArgumentException("Invalid file type: " + image.getOriginalFilename());
                }

                String fileExtension = getFileExtension(image.getOriginalFilename());
                // Use new naming: item{productId}-{index}.{ext}
                String fileName = String.format("item%d-%d%s", productId, i + 1, fileExtension);
                Path filePath = uploadPath.resolve(fileName);

                try {
                    Files.write(filePath, image.getBytes());
                    String relativePath = String.format("images/product-%d/%s", productId, fileName);
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

    public void deleteProductImages(Long id)
    {
        Path productDir = Paths.get(BASE_STATIC_DIR,"product-"+id);

        try {
            if(Files.exists(productDir))
            {
                Files.walk(productDir)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            throw new FileStorageException(" Cannot delete product images with id: "+id, e);
        }
    }

    private String getFileExtension(String fileName) 
    {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            log.warn("No file extension found for file: {}, defaulting to .jpg", fileName);
            return ".jpg";
        }
        String extension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
        if (!extension.matches("\\.(jpg|jpeg|png|gif)$")) {
            log.warn("Unrecognized image extension: {}, defaulting to .jpg", extension);
            return ".jpg";
        }
        return extension;
    }
}