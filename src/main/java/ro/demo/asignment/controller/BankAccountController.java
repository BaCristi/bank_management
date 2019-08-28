package ro.demo.asignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.exception.EqualAccountsException;
import ro.demo.asignment.model.BankAccountDepositRequest;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.BankAccountTransferRequest;
import ro.demo.asignment.model.GenericEmailRequestModel;
import ro.demo.asignment.service.BankAccountService;
import ro.demo.asignment.validator.UserExistsValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
@RequiredArgsConstructor
public class BankAccountController {
    @NotNull
    private final BankAccountService bankAccountService;
    @NotNull
    private final UserExistsValidator userExistsValidator;

    @PostMapping("/create")
    public ResponseEntity<BankAccountResponseModel> createBankAccount(
            @RequestBody @Valid final GenericEmailRequestModel request){
        final User user = userExistsValidator.checkIfUserExists(request.getEmail());
        return ResponseEntity.ok(bankAccountService.createBankAccount(user));
    }

    @GetMapping("/balance")
    public ResponseEntity<BankAccountResponseModel> getAccountBankBalance(@RequestParam final String email,
                                                                          @RequestParam final String accountName){
        return ResponseEntity.ok(bankAccountService.getBalanceForAccount(email,accountName));
    }

    @PostMapping("/balance/deposit")
    public ResponseEntity<BankAccountResponseModel> depositInBankAccount(
            @RequestBody @Valid final BankAccountDepositRequest request){
        return ResponseEntity.ok(bankAccountService.depositFunds(request));
    }

    @PostMapping("/balance/transfer")
    public ResponseEntity<List<BankAccountResponseModel>> transferToBankAccount(
            @RequestBody @Valid final BankAccountTransferRequest request){
        if (request.getAccountNameSource().equalsIgnoreCase(request.getAccountNameDestination())){
           throw new EqualAccountsException();
        }
        return ResponseEntity.ok(bankAccountService.transferFunds(request));
    }
}
