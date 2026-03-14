package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.Comment;
import practica.turismo.entity.Photo;
import practica.turismo.service.CommentService;
import practica.turismo.service.PhotoService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CommentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentService commentService;

    @Mock
    private PhotoService photoService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @Test
    void createComment_ShouldReturnCreated() throws Exception {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Excelente foto!");
        Photo photo = new Photo();
        photo.setId(10L);
        comment.setPhoto(photo);

        when(photoService.findById(10L)).thenReturn(java.util.Optional.of(photo));
        when(commentService.createComment(any(Comment.class))).thenReturn(comment);

        mockMvc.perform(post("/api/v1/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\": \"Excelente foto!\", \"photoId\": 10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("Excelente foto!"));
    }

    @Test
    void getCommentsByPhoto_ShouldReturnList() throws Exception {
        Comment c1 = new Comment();
        c1.setId(1L);
        Comment c2 = new Comment();
        c2.setId(2L);

        when(commentService.findByPhotoId(10L)).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/api/v1/comments/photo/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
