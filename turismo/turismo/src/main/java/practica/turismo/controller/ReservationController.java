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
import practica.turismo.dto.ReservationDTO;
import practica.turismo.entity.Reservation;
import practica.turismo.entity.Tour;
import practica.turismo.entity.User;
import practica.turismo.service.ReservationService;
import practica.turismo.service.TourService;
import practica.turismo.service.UserService;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) {
        User user = userService.findById(reservationDTO.getUserId()).orElseThrow(); // Asume auth user
        Tour tour = tourService.findById(reservationDTO.getTourId()).orElseThrow();
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTour(tour);
        reservation.setDate(reservationDTO.getDate());
        reservation.setGroupSize(reservationDTO.getGroupSize());
        Reservation created = reservationService.createReservation(reservation);
        return new ResponseEntity<>(new ReservationDTO(created), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@PathVariable Long userId) {
        List<ReservationDTO> reservations = reservationService.findByUserId(userId).stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservations);
    }
}
