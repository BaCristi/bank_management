package ro.demo.asignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@Data
public class BankAccountTransferRequest {
    @Email(message = "Email should be valid")
    @Size(min = 5, max = 255, message = "Must be between 5 and 255 characters")
    @NotNull
    private String email;

    @Size(min = 5, max = 255, message = "Must be between 5 and 255 characters")
    @NotNull
    private String accountNameSource;

    @Size(min = 5, max = 255, message = "Must be between 5 and 255 characters")
    @NotNull
    private String accountNameDestination;

    @NotNull
    @Min(value = 1, message = "Minim deposit amount is 1")
    @Max(value = 20000, message = "Max deposit amount is 20000")
    private Integer transferQuantity;
}
