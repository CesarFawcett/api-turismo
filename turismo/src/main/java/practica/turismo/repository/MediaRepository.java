package practica.turismo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practica.turismo.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByTourId(Long tourId);

    List<Media> findByPlaceId(Long placeId);
}
