package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.Reservation;
import practica.turismo.entity.Tour;
import practica.turismo.entity.User;
import practica.turismo.service.ReservationService;
import practica.turismo.service.TourService;
import practica.turismo.service.UserService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @Mock
    private UserService userService;

    @Mock
    private TourService tourService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    void createReservation_ShouldReturnCreated() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setDate(LocalDate.of(2026, 12, 1));
        
        User user = new User();
        user.setId(2L);
        reservation.setUser(user);
        
        Tour tour = new Tour();
        tour.setId(3L);
        reservation.setTour(tour);

        when(userService.findById(2L)).thenReturn(java.util.Optional.of(user));
        when(tourService.findById(3L)).thenReturn(java.util.Optional.of(tour));
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        mockMvc.perform(post("/api/v1/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 2, \"tourId\": 3, \"date\": \"2026-12-01\", \"groupSize\": 2}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getReservationsByUser_ShouldReturnList() throws Exception {
        Reservation r1 = new Reservation();
        r1.setId(1L);
        Reservation r2 = new Reservation();
        r2.setId(2L);

        when(reservationService.findByUserId(2L)).thenReturn(Arrays.asList(r1, r2));

        mockMvc.perform(get("/api/v1/reservations/user/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
