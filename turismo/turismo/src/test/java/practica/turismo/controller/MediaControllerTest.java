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

import practica.turismo.entity.Media;
import practica.turismo.service.MediaService;
import practica.turismo.service.UploadService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MediaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MediaService mediaService;

    @Mock
    private UploadService uploadService;

    @InjectMocks
    private MediaController mediaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mediaController).build();
    }

    @Test
    void uploadMedia_ShouldReturnCreated() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "video.mp4", "video/mp4", "test video content".getBytes());
        MockMultipartFile mediaDtoPart = new MockMultipartFile(
                "mediaDTO",
                "",
                "application/json",
                "{\"url\": \"http://example.com/video.mp4\", \"type\": \"VIDEO\", \"tourId\": 1}".getBytes());

        Media media = new Media();
        media.setId(1L);
        media.setUrl("http://example.com/video.mp4");

        when(uploadService.uploadFile(any())).thenReturn("http://example.com/video.mp4");
        when(mediaService.uploadMedia(any(), any(Media.class))).thenReturn(media);

        mockMvc.perform(multipart("/api/v1/media")
                .file(file)
                .file(mediaDtoPart))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.url").value("http://example.com/video.mp4"));
    }

    @Test
    void getMediaByTour_ShouldReturnList() throws Exception {
        Media m1 = new Media();
        m1.setId(1L);
        Media m2 = new Media();
        m2.setId(2L);

        when(mediaService.findByTourId(1L)).thenReturn(Arrays.asList(m1, m2));

        mockMvc.perform(get("/api/v1/media/tour/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
