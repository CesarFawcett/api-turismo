package practica.turismo.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import practica.turismo.entity.Comment;

public class CommentDTO {
    private Long id;

    @NotNull(message = "La foto asociada es requerida")
    private Long photoId;

    @NotBlank(message = "El contenido del comentario es requerido")
    private String content;

    private LocalDateTime createdAt;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.photoId = comment.getPhoto() != null ? comment.getPhoto().getId() : null;
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
