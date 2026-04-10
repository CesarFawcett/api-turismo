package practica.turismo.dto;

import jakarta.validation.constraints.NotBlank;
import practica.turismo.entity.Media;

public class MediaDTO {
    private Long id;

    @NotBlank(message = "La URL del media es requerida")
    private String url;

    @NotBlank(message = "El tipo (photo/video) es requerido")
    private String type;

    private Long tourId;

    private Long placeId;

    private Double latitude;

    private Double longitude;

    public MediaDTO() {
    }

    public MediaDTO(Media media) {
        this.id = media.getId();
        this.url = media.getUrl();
        this.type = media.getType();
        this.tourId = media.getTour() != null ? media.getTour().getId() : null;
        this.placeId = media.getPlace() != null ? media.getPlace().getId() : null;
        this.latitude = media.getLatitude();
        this.longitude = media.getLongitude();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
