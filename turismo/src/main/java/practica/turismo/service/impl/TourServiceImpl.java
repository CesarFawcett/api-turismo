package practica.turismo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.turismo.entity.Tour;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.TourRepository;
import practica.turismo.service.TourService;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    private TourRepository tourRepository;

    @Override
    public Tour createTour(Tour tour) {
        if (tour.getBasePrice() <= 0) {
            throw new ApiException("Por favor, ingresa un precio válido mayor a cero.");
        }
        return tourRepository.save(tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public List<Tour> findByPlaceId(Long placeId) {
        return tourRepository.findByPlaceId(placeId);
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll(); // Delega al JpaRepository, que retorna todos los tours
    }
}
