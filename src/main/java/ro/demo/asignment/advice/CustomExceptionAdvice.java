package ro.demo.asignment.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.demo.asignment.exception.*;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

@RestControllerAdvice
public class CustomExceptionAdvice {
    private static final String USER_ALREADY_REGISTERED = "User already registered.";
    private static final String USER_NOT_REGISTERED = "User not registered.";
    private static final String BANK_ACCOUNT_NOT_FOUND = "Bank account not found";
    private static final String NOT_ENOUGH_FUNDS = "Not enough funds";
    public static final String CAN_T_TRANSFER_TO_THE_SAME_BANK_ACCOUNT = "Can't transfer to the same bank account";

    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<Map<String, List<String>>> notUniqueException(final NotUniqueException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap("message", singletonList(USER_ALREADY_REGISTERED)));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> userNotRegisteredException(final UserNotFoundException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap("message", singletonList(USER_NOT_REGISTERED)));
    }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> bankAccountNotFoundException(final BankAccountNotFoundException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap("message", singletonList(BANK_ACCOUNT_NOT_FOUND)));
    }

    @ExceptionHandler(BankAccountBalanceInsufficientException.class)
    public ResponseEntity<Map<String, List<String>>> bankAccountNotFoundException(final BankAccountBalanceInsufficientException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap("message", singletonList(NOT_ENOUGH_FUNDS)));
    }

    @ExceptionHandler(EqualAccountsException.class)
    public ResponseEntity<Map<String, List<String>>> accountAreEqualException(final EqualAccountsException e) {
        return ResponseEntity.badRequest()
                .body(singletonMap("message", singletonList(CAN_T_TRANSFER_TO_THE_SAME_BANK_ACCOUNT)));
    }
}
