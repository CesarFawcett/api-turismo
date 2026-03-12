package practica.turismo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.exception.ApiException;

@Service
public class UploadService {

    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException("Archivo vacío. Intenta subir uno válido.");
        }
        return "https://example.com/uploaded/" + file.getOriginalFilename(); // Placeholder
    }
}
