package iti.jets.services;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

public interface CloudinaryService {
    Map<String, String> uploadFile(MultipartFile file, String folder);
    List<Map<String, String>> uploadFiles(List<MultipartFile> files, String folder);
    void deleteFile(String publicId);
    void deleteFiles(List<String> publicIds);
}
