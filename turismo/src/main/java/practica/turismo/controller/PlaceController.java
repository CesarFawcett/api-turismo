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
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import practica.turismo.dto.PlaceDTO;
import practica.turismo.entity.Place;
import practica.turismo.service.PlaceService;

@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceDTO> createPlace(@Valid @RequestBody PlaceDTO placeDTO) {
        Place place = new Place();
        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLatitude(placeDTO.getLatitude());
        place.setLongitude(placeDTO.getLongitude());
        Place created = placeService.createPlace(place);
        return new ResponseEntity<>(new PlaceDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDTO> getPlaceById(@PathVariable Long id) {
        return placeService.findById(id)
                .map(place -> new ResponseEntity<>(new PlaceDTO(place), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PlaceDTO>> getAllPlaces() {
        List<PlaceDTO> places = placeService.findAll().stream()
                .map(PlaceDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(places);
    }

}
