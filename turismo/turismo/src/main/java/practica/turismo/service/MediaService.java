package practica.turismo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.entity.Media;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.MediaRepository;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public Media uploadMedia(MultipartFile file, Media media) {
        if (file.getSize() > 50 * 1024 * 1024) { // 50MB límite
            throw new ApiException("El archivo excede el límite de 50MB. Intenta comprimirlo.");
        }
        return mediaRepository.save(media);
    }

    public List<Media> findByTourId(Long tourId) {
        return mediaRepository.findByTourId(tourId);
    }
}
