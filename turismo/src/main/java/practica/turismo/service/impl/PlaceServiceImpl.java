package practica.turismo.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practica.turismo.entity.Place;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.PlaceRepository;
import practica.turismo.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public Place createPlace(Place place) {
        if (placeRepository.findById(place.getId()).isPresent()) {
            throw new ApiException("¡Ups! Este lugar ya existe.");
        }
        return placeRepository.save(place);
    }

    @Override
    public Optional<Place> findById(Long id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }
}
