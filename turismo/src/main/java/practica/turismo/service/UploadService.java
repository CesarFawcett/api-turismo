package practica.turismo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadFile(MultipartFile file);
}
