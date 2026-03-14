package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.Photo;
import practica.turismo.entity.Tour;
import practica.turismo.service.PhotoService;
import practica.turismo.service.TourService;
import practica.turismo.service.UploadService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AlbumControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhotoService photoService;

    @Mock
    private UploadService uploadService;

    @Mock
    private TourService tourService;

    @InjectMocks
    private AlbumController albumController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
    }

    @Test
    void uploadPhoto_ShouldReturnCreated() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "test image content".getBytes());
        
        Tour tour = new Tour();
        tour.setId(1L);

        Photo photo = new Photo();
        photo.setId(10L);
        photo.setUrl("http://example.com/image.jpg");
        photo.setTour(tour);
        photo.setDate(LocalDate.of(2026, 10, 10));

        when(uploadService.uploadFile(any())).thenReturn("http://example.com/image.jpg");
        when(tourService.findById(1L)).thenReturn(Optional.of(tour));
        when(photoService.uploadPhoto(any(), any(Photo.class))).thenReturn(photo);

        MockMultipartFile photoDtoPart = new MockMultipartFile(
                "photoDTO",
                "",
                "application/json",
                "{\"tourId\": 1, \"date\": \"2026-10-10\"}".getBytes());

        mockMvc.perform(multipart("/api/v1/albums/photos")
                .file(file)
                .file(photoDtoPart))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.url").value("http://example.com/image.jpg"));
    }

    @Test
    void getGalleryByTour_ShouldReturnList() throws Exception {
        Photo p1 = new Photo();
        p1.setId(1L);
        p1.setApproved(true);
        
        Photo p2 = new Photo();
        p2.setId(2L);
        p2.setApproved(true);

        when(photoService.findApprovedByTourId(5L)).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/api/v1/albums/tour/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
