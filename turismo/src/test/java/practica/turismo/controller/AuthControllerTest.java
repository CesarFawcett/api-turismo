package practica.turismo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import practica.turismo.entity.User;
import practica.turismo.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void register_ShouldReturnCreatedUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");
        user.setName("Test User");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@test.com\", \"password\": \"123456\", \"name\": \"Test User\", \"role\": \"TOURIST\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }

    @Test
    void login_ShouldReturnToken() throws Exception {
        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"test@test.com\", \"password\": \"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("JWT token here"));
    }
}
