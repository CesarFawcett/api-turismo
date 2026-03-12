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
import practica.turismo.dto.CommentDTO;
import practica.turismo.entity.Comment;
import practica.turismo.entity.Photo;
import practica.turismo.service.CommentService;
import practica.turismo.service.PhotoService;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        Photo photo = photoService.findById(commentDTO.getPhotoId()).orElseThrow();
        Comment comment = new Comment();
        comment.setPhoto(photo);
        comment.setContent(commentDTO.getContent());
        // Set user desde auth
        Comment created = commentService.createComment(comment);
        return new ResponseEntity<>(new CommentDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/photo/{photoId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPhoto(@PathVariable Long photoId) {
        List<CommentDTO> comments = commentService.findByPhotoId(photoId).stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(comments);
    }
}
