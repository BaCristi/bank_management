package ro.demo.asignment.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ResponseEntityExceptionHandlerImpl extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        Map<String, List<String>> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            FieldError fieldError = (FieldError) objectError;

            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            if (errorMap.containsKey(field)) {
                errorMap.get(field).add(message);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(message);
                errorMap.put(field, list);
            }
        });
        return ResponseEntity.badRequest().body(errorMap);
    }
}
