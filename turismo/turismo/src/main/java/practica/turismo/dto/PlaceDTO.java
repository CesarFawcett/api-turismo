package practica.turismo.dto;

import jakarta.validation.constraints.NotBlank;
import practica.turismo.entity.Place;

public class PlaceDTO {

    private Long id;

    @NotBlank(message = "El nombre del lugar es requerido")
    private String name;

    private String description;

    private Double latitude;

    private Double longitude;

    public PlaceDTO() {
    }

    public PlaceDTO(Place place) {
        this.id = place.getId();
        this.name = place.getName();
        this.description = place.getDescription();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
