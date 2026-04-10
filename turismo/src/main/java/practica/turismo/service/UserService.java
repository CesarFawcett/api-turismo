package practica.turismo.service;

import java.util.List;
import java.util.Optional;
import practica.turismo.entity.User;

public interface UserService {
    User createUser(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    Optional<User> findById(Long id);
}
