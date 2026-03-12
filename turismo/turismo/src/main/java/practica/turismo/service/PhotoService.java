package practica.turismo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.entity.Photo;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo uploadPhoto(MultipartFile file, Photo photo) {
        String contentType = file.getContentType();
        if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
            throw new ApiException("Solo aceptamos JPG/PNG. Convierte tu archivo e inténtalo de nuevo.");
        }
        return photoRepository.save(photo);
    }

    public List<Photo> findApprovedByTourId(Long tourId) {
        return photoRepository.findByTourIdAndApproved(tourId, true);
    }

    public Photo approvePhoto(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new ApiException("Foto no encontrada."));
        photo.setApproved(true);
        return photoRepository.save(photo);
    }

    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id); // Delega al repository de JPA
    }
}
