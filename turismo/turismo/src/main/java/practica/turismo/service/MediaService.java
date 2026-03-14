package practica.turismo.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.entity.Media;

public interface MediaService {
    Media uploadMedia(MultipartFile file, Media media);
    List<Media> findByTourId(Long tourId);
}
