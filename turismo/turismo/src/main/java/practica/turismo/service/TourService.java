package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import practica.turismo.entity.Tour;

public interface TourService {
    Tour createTour(Tour tour);
    Optional<Tour> findById(Long id);
    List<Tour> findByPlaceId(Long placeId);
    List<Tour> findAll();
}
