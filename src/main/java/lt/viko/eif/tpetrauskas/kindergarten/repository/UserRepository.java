package lt.viko.eif.tpetrauskas.kindergarten.repository;

import lt.viko.eif.tpetrauskas.kindergarten.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
