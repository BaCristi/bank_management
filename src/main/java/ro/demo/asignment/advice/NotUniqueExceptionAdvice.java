package ro.demo.asignment.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.demo.asignment.exception.NotUniqueException;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

@RestControllerAdvice
public class NotUniqueExceptionAdvice {
    private static final String USER_ALREADY_REGISTERED = "Utilizator deja inregistrat";

    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<Map<String, List<String>>> notUniqueException(final NotUniqueException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap(e.getField(), singletonList(USER_ALREADY_REGISTERED)));
    }
}
