package practica.turismo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import practica.turismo.entity.Photo;

public class PhotoDTO {
    private Long id;

    @NotBlank(message = "La URL de la foto es requerida")
    private String url;

    @NotNull(message = "El tour asociado es requerido")
    private Long tourId;

    @NotNull(message = "La fecha es requerida")
    private LocalDate date;

    private Boolean approved;

    public PhotoDTO() {
    }

    public PhotoDTO(Photo photo) {
        this.id = photo.getId();
        this.url = photo.getUrl();
        this.tourId = photo.getTour() != null ? photo.getTour().getId() : null;
        this.date = photo.getDate();
        this.approved = photo.getApproved();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
