package practica.turismo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.entity.Media;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.MediaRepository;
import practica.turismo.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public Media uploadMedia(MultipartFile file, Media media) {
        if (file.getSize() > 50 * 1024 * 1024) { // 50MB límite
            throw new ApiException("El archivo excede el límite de 50MB. Intenta comprimirlo.");
        }
        return mediaRepository.save(media);
    }

    @Override
    public List<Media> findByTourId(Long tourId) {
        return mediaRepository.findByTourId(tourId);
    }
}
