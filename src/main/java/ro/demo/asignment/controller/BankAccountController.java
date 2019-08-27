package ro.demo.asignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.demo.asignment.entity.User;
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

}
