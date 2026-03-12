package practica.turismo.dto;

import java.util.Map;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import practica.turismo.entity.Tour;

public class TourDTO {

    private Long id;

    @NotBlank(message = "El nombre del tour es requerido")
    private String name;

    private String description;

    @NotNull(message = "El lugar asociado es requerido")
    private Long placeId;

    @Min(value = 0, message = "El precio base debe ser positivo")
    private Double basePrice;

    @Min(value = 1, message = "La duración debe ser al menos 1 hora/día")
    private Integer durationHours;

    private Map<String, Double> promotions;

    public TourDTO() {
    }

    public TourDTO(Tour tour) {
        this.id = tour.getId();
        this.name = tour.getName();
        this.description = tour.getDescription();
        this.placeId = tour.getPlace() != null ? tour.getPlace().getId() : null;
        this.basePrice = tour.getBasePrice();
        this.durationHours = tour.getDurationHours();
        this.promotions = tour.getPromotions();
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

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public Map<String, Double> getPromotions() {
        return promotions;
    }

    public void setPromotions(Map<String, Double> promotions) {
        this.promotions = promotions;
    }
}
