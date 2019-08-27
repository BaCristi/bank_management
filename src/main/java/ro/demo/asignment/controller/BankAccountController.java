package ro.demo.asignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.model.BankAccountDepositRequest;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.EmailRequest;
import ro.demo.asignment.service.BankAccountService;
import ro.demo.asignment.validator.UserExistsValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
            @RequestBody @Valid final EmailRequest request){
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
        return ResponseEntity.ok(bankAccountService.deposit(request));
    }

    @PostMapping("/balance/transfer")
    public ResponseEntity<BankAccountResponseModel> transferToBankAccount(
            @RequestBody @Valid final BankAccountDepositRequest request){
        return ResponseEntity.ok(bankAccountService.deposit(request));
    }
}
