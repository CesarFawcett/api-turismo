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
import practica.turismo.service.PlaceService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlaceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private PlaceController placeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(placeController).build();
    }

    @Test
    void createPlace_ShouldReturnCreated() throws Exception {
        Place place = new Place();
        place.setId(1L);
        place.setName("Ecuador");
        place.setDescription("Lugar hermoso");

        when(placeService.createPlace(any(Place.class))).thenReturn(place);

        mockMvc.perform(post("/api/v1/places")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Ecuador\", \"description\": \"Lugar hermoso\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ecuador"));
    }

    @Test
    void getAllPlaces_ShouldReturnList() throws Exception {
        Place p1 = new Place();
        p1.setId(1L);
        Place p2 = new Place();
        p2.setId(2L);

        when(placeService.findAll()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/api/v1/places"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
