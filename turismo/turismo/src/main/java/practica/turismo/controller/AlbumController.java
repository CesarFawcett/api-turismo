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
import org.springframework.web.multipart.MultipartFile;
import practica.turismo.dto.PhotoDTO;
import practica.turismo.entity.Photo;
import practica.turismo.entity.Tour;
import practica.turismo.service.PhotoService;
import practica.turismo.service.TourService;
import practica.turismo.service.UploadService;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private TourService tourService;

    @PostMapping("/photos")
    public ResponseEntity<PhotoDTO> uploadPhoto(@RequestParam("file") MultipartFile file,
            @RequestBody PhotoDTO photoDTO) {
        String url = uploadService.uploadFile(file);
        Tour tour = tourService.findById(photoDTO.getTourId()).orElseThrow();
        Photo photo = new Photo();
        photo.setUrl(url);
        photo.setTour(tour);
        photo.setDate(photoDTO.getDate());
        // Set user desde auth
        Photo created = photoService.uploadPhoto(file, photo);
        return new ResponseEntity<>(new PhotoDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/tour/{tourId}")
    public ResponseEntity<List<PhotoDTO>> getGalleryByTour(@PathVariable Long tourId) {
        List<PhotoDTO> photos = photoService.findApprovedByTourId(tourId).stream()
                .map(PhotoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(photos);
    }
}
