package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.entity.Photo;

public interface PhotoService {
    Photo uploadPhoto(MultipartFile file, Photo photo);
    List<Photo> findApprovedByTourId(Long tourId);
    Photo approvePhoto(Long id);
    Optional<Photo> findById(Long id);
}
