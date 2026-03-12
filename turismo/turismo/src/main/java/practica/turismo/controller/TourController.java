package practica.turismo.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import practica.turismo.dto.TourDTO;
import practica.turismo.entity.Place;
import practica.turismo.entity.Tour;
import practica.turismo.service.PlaceService;
import practica.turismo.service.TourService;

@RestController
@RequestMapping("/api/v1/tours")
public class TourController {
    @Autowired
    private TourService tourService;

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<TourDTO> createTour(@Valid @RequestBody TourDTO tourDTO) {
        Place place = placeService.findById(tourDTO.getPlaceId())
                .orElseThrow(() -> new RuntimeException("Lugar no encontrado")); // Usa ApiException
        Tour tour = new Tour();
        tour.setName(tourDTO.getName());
        tour.setDescription(tourDTO.getDescription());
        tour.setPlace(place);
        tour.setBasePrice(tourDTO.getBasePrice());
        tour.setDurationHours(tourDTO.getDurationHours());
        tour.setPromotions(tourDTO.getPromotions());
        Tour created = tourService.createTour(tour);
        return new ResponseEntity<>(new TourDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Long id) {
        return tourService.findById(id)
                .map(tour -> new ResponseEntity<>(new TourDTO(tour), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TourDTO>> getAllTours(@RequestParam(required = false) Long placeId) {
        List<Tour> tours = placeId != null ? tourService.findByPlaceId(placeId) : tourService.findAll();
        List<TourDTO> dtos = tours.stream().map(TourDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
