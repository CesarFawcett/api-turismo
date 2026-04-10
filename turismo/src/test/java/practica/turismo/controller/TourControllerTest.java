package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.Place;
import practica.turismo.entity.Tour;
import practica.turismo.service.PlaceService;
import practica.turismo.service.TourService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TourControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TourService tourService;

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private TourController tourController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tourController).build();
    }

    @Test
    void createTour_ShouldReturnCreated() throws Exception {
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setName("Tour Centro Histórico");
        tour.setBasePrice(50.0);
        Place place = new Place();
        place.setId(10L);
        tour.setPlace(place);

        when(placeService.findById(10L)).thenReturn(java.util.Optional.of(place));
        when(tourService.createTour(any(Tour.class))).thenReturn(tour);

        mockMvc.perform(post("/api/v1/tours")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Tour Centro Histórico\", \"basePrice\": 50.0, \"placeId\": 10, \"durationHours\": 4}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Tour Centro Histórico"));
    }

    @Test
    void getToursByPlace_ShouldReturnList() throws Exception {
        Tour t1 = new Tour();
        t1.setId(1L);
        Tour t2 = new Tour();
        t2.setId(2L);

        when(tourService.findByPlaceId(10L)).thenReturn(Arrays.asList(t1, t2));

        mockMvc.perform(get("/api/v1/tours?placeId=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
