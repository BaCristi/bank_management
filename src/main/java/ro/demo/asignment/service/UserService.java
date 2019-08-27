package ro.demo.asignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.GenericEmailRequestModel;
import ro.demo.asignment.repository.UserRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class UserService {
    @NotNull
    private final UserRepository userRepository;
    @NotNull
    private final BankAccountService bankAccountService;

    @Transactional
    public BankAccountResponseModel registerUser(final GenericEmailRequestModel request){
        final User user = new User(request.getEmail());
        final User savedUser = userRepository.save(user);

        return bankAccountService.createBankAccount(savedUser);
    }
}
