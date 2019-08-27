package ro.demo.asignment.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.demo.asignment.exception.NotUniqueException;
import ro.demo.asignment.repository.UserRepository;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class UserUniqueValidator {
    @NotNull
    private final UserRepository userRepository;

    public void checkUserExists(final String email) {
        final boolean userWithEmailExists = userRepository.existsByEmail(email);
        if (userWithEmailExists){
            throw new NotUniqueException("email");
        }
    }
}
