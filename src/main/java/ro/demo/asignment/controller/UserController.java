package ro.demo.asignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.demo.asignment.entity.BankAccount;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.RegisterUserRequest;
import ro.demo.asignment.service.UserService;
import ro.demo.asignment.validator.UserUniqueValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @NotNull
    private final UserService userService;
    @NotNull
    private final UserUniqueValidator userUniqueValidator;

    @PostMapping("/register")
    public ResponseEntity<BankAccountResponseModel> registerUser(
            @RequestBody @Valid final RegisterUserRequest registerRequest){
        userUniqueValidator.checkUserExists(registerRequest.getEmail());
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

}
