package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.turismo.entity.Tour;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.TourRepository;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public Tour createTour(Tour tour) {
        if (tour.getBasePrice() <= 0) {
            throw new ApiException("Por favor, ingresa un precio válido mayor a cero.");
        }
        return tourRepository.save(tour);
    }

    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    public List<Tour> findByPlaceId(Long placeId) {
        return tourRepository.findByPlaceId(placeId);
    }

    public List<Tour> findAll() {
        return tourRepository.findAll(); // Delega al JpaRepository, que retorna todos los tours
    }
}
