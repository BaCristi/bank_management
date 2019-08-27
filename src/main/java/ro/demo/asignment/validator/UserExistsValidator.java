package ro.demo.asignment.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.exception.UserNotFoundException;
import ro.demo.asignment.repository.UserRepository;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class UserExistsValidator {
    @NotNull
    private final UserRepository userRepository;

    public User checkIfUserExists(final String email) {
        return userRepository
                .findByEmail(email).orElseThrow(() -> new UserNotFoundException("email"));
    }
}
