package ro.demo.asignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class GenericEmailRequestModel {
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 255, message = "Must be between 5 and 255 characters")
    @NotNull
    private String email;
}
