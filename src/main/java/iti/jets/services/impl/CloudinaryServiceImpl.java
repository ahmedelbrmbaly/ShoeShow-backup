package iti.jets.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import iti.jets.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public Map<String, String> uploadFile(MultipartFile file, String folder) {
        try {
            Map<String, Object> params = ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "auto"
            );

            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);

            Map<String, String> result = new HashMap<>();
            result.put("url", uploadResult.get("secure_url").toString());
            result.put("publicId", uploadResult.get("public_id").toString());
            return result;
        } catch (IOException e) {
            log.error("Error uploading file to Cloudinary", e);
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public List<Map<String, String>> uploadFiles(List<MultipartFile> files, String folder) {
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            results.add(uploadFile(file, folder));
        }
        return results;
    }

    @Override
    public void deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            log.error("Error deleting file from Cloudinary", e);
            throw new RuntimeException("Failed to delete file", e);
        }
    }

    @Override
    public void deleteFiles(List<String> publicIds) {
        publicIds.forEach(this::deleteFile);
    }
}
