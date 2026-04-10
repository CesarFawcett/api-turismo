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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import practica.turismo.dto.MediaDTO;
import practica.turismo.entity.Media;
import practica.turismo.entity.Place;
import practica.turismo.entity.Tour;
import practica.turismo.service.MediaService;
import practica.turismo.service.PlaceService;
import practica.turismo.service.TourService;
import practica.turismo.service.UploadService;

@RestController
@RequestMapping("/api/v1/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private TourService tourService;

    @Autowired
    private PlaceService placeService;

    @PostMapping
    public ResponseEntity<MediaDTO> uploadMedia(@RequestParam("file") MultipartFile file,
            @Valid @RequestPart("mediaDTO") MediaDTO mediaDTO) {
        String url = uploadService.uploadFile(file);
        Media media = new Media();
        media.setUrl(url);
        media.setType(mediaDTO.getType());
        if (mediaDTO.getTourId() != null) {
            Tour tour = tourService.findById(mediaDTO.getTourId()).orElseThrow();
            media.setTour(tour);
        }
        if (mediaDTO.getPlaceId() != null) {
            Place place = placeService.findById(mediaDTO.getPlaceId()).orElseThrow();
            media.setPlace(place);
        }
        media.setLatitude(mediaDTO.getLatitude());
        media.setLongitude(mediaDTO.getLongitude());
        Media created = mediaService.uploadMedia(file, media); // Actualiza si necesitas
        return new ResponseEntity<>(new MediaDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<MediaDTO>> getMediaByTour(@PathVariable Long tourId) {
        List<MediaDTO> media = mediaService.findByTourId(tourId).stream()
                .map(MediaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(media);
    }
}
