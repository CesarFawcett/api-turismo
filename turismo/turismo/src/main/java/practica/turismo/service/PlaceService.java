package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.turismo.entity.Place;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.PlaceRepository;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place createPlace(Place place) {

        if (placeRepository.findById(place.getId()).isPresent()) {
            throw new ApiException("¡Ups! Este lugar ya existe.");
        }
        return placeRepository.save(place);
    }

    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }
}
