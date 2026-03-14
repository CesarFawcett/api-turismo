package practica.turismo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practica.turismo.entity.Reservation;
import practica.turismo.entity.Tour;
import practica.turismo.exception.ApiException;
import practica.turismo.repository.ReservationRepository;
import practica.turismo.repository.TourRepository;
import org.springframework.transaction.annotation.Transactional;
import practica.turismo.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TourRepository tourRepository;

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        tourRepository.findById(reservation.getTour().getId())
                .orElseThrow(() -> new ApiException("Tour no encontrado."));

        // Chequear disponibilidad: asumamos límite de 10 por fecha para MVP
        List<Reservation> existing = reservationRepository.findByTourAndDate(reservation.getTour().getId(),
                reservation.getDate());
        if (existing.size() >= 10) {
            throw new ApiException("Esta fecha ya está llena. ¿Quieres ver fechas alternativas?");
        }

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}
