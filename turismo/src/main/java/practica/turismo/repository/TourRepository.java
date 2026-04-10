package practica.turismo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practica.turismo.entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findByPlaceId(Long placeId);
}
