package ro.demo.asignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.demo.asignment.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(final String email);

    Optional<User> findByEmail(final String email);
}
