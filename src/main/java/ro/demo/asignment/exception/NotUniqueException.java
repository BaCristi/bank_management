package ro.demo.asignment.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
public class NotUniqueException extends RuntimeException {
    @NotNull
    private final String field;
}
