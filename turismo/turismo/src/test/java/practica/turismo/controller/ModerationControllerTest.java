package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.Photo;
import practica.turismo.service.PhotoService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ModerationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhotoService photoService;

    @InjectMocks
    private ModerationController moderationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moderationController).build();
    }

    @Test
    void approvePhoto_ShouldReturnOk() throws Exception {
        Photo photo = new Photo();
        photo.setId(5L);
        photo.setApproved(true);

        when(photoService.approvePhoto(5L)).thenReturn(photo);

        mockMvc.perform(put("/api/v1/moderation/photos/5/approve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.approved").value(true));
    }
}
