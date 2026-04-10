package practica.turismo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import practica.turismo.dto.UserDTO;
import practica.turismo.entity.User;
import practica.turismo.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    // Placeholder para JWT token service si configuras Security

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        User user = new User(); // Mapear DTO a entidad (usa builder o manual)
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        // Set password desde DTO si incluyes (no en este DTO por seguridad)
        User created = userService.createUser(user);
        return new ResponseEntity<>(new UserDTO(created), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        // Lógica de login: verifica credenciales, genera JWT
        // Por ahora, placeholder
        return ResponseEntity.ok("JWT token here");
    }
}
