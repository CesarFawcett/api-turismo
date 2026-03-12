package practica.turismo.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import practica.turismo.entity.Reservation;

public class ReservationDTO {
    private Long id;

    @NotNull(message = "El usuario es requerido")
    private Long userId;

    @NotNull(message = "El tour es requerido")
    private Long tourId;

    @NotNull(message = "La fecha es requerida")
    @FutureOrPresent(message = "La fecha debe ser hoy o futura")
    private LocalDate date;

    @Min(value = 1, message = "El tamaño del grupo debe ser al menos 1")
    private Integer groupSize;

    private Reservation.Status status;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.userId = reservation.getUser() != null ? reservation.getUser().getId() : null;
        this.tourId = reservation.getTour() != null ? reservation.getTour().getId() : null;
        this.date = reservation.getDate();
        this.groupSize = reservation.getGroupSize();
        this.status = reservation.getStatus();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public Reservation.Status getStatus() {
        return status;
    }

    public void setStatus(Reservation.Status status) {
        this.status = status;
    }
}
