package practica.turismo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.exception.ApiException;
import practica.turismo.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException("Archivo vacío. Intenta subir uno válido.");
        }
        return "https://example.com/uploaded/" + file.getOriginalFilename(); // Placeholder
    }
}
