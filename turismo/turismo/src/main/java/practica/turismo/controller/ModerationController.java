package practica.turismo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practica.turismo.dto.PhotoDTO;
import practica.turismo.entity.Photo;
import practica.turismo.service.PhotoService;

@RestController
@RequestMapping("/api/v1/moderation")
public class ModerationController {
    @Autowired
    private PhotoService photoService;

    @PutMapping("/photos/{id}/approve")
    public ResponseEntity<PhotoDTO> approvePhoto(@PathVariable Long id) {
        Photo approved = photoService.approvePhoto(id);
        return ResponseEntity.ok(new PhotoDTO(approved));
    }
}
