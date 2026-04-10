package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import practica.turismo.entity.Place;

public interface PlaceService {
    Place createPlace(Place place);
    Optional<Place> findById(Long id);
    List<Place> findAll();
}
