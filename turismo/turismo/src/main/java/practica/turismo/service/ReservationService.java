package practica.turismo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practica.turismo.entity.Reservation;
import practica.turismo.entity.Tour;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.ReservationRepository;
import practica.turismo.repository.TourRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TourRepository tourRepository;

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        Tour tour = tourRepository.findById(reservation.getTour().getId())
                .orElseThrow(() -> new ApiException("Tour no encontrado."));

        // Chequear disponibilidad: asumamos límite de 10 por fecha para MVP
        List<Reservation> existing = reservationRepository.findByTourAndDate(reservation.getTour().getId(),
                reservation.getDate());
        if (existing.size() >= 10) {
            throw new ApiException("Esta fecha ya está llena. ¿Quieres ver fechas alternativas?");
        }

        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
