package practica.turismo.service;

import java.util.List;
import practica.turismo.entity.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> findByUserId(Long userId);
}
