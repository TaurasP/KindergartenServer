package lt.viko.eif.tpetrauskas.kindergarten.service;

import lt.viko.eif.tpetrauskas.kindergarten.config.JwtUtil;
import lt.viko.eif.tpetrauskas.kindergarten.model.User;
import lt.viko.eif.tpetrauskas.kindergarten.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<String> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> login(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if (new BCryptPasswordEncoder(12).matches(user.getPassword(), existingUser.get().getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok("Bearer " + token);
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("User not found");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
