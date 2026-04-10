package practica.turismo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practica.turismo.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByTourIdAndApproved(Long tourId, Boolean approved); // Fotos aprobadas para galería pública

    List<Photo> findByUserId(Long userId); // Fotos de un usuario
}
