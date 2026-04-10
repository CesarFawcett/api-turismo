package practica.turismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practica.turismo.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
