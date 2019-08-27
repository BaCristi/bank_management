package ro.demo.asignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.demo.asignment.entity.BankAccount;
import ro.demo.asignment.entity.BankAccountRepository;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.model.BankAccountResponseModel;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    @NotNull
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public BankAccountResponseModel createBankAccount(final User savedUser) {
        final BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(savedUser);
        bankAccount.setAccountName(generateUniqueAccountName(savedUser));
        bankAccount.setBalance(0);
        final BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return new BankAccountResponseModel(savedBankAccount.getAccountName(), savedBankAccount.getBalance());
    }

    private String generateUniqueAccountName(final User savedUser) {
        return savedUser.getEmail().split("@")[0] + "_" + UUID.randomUUID().toString().substring(0, 7);
    }
}
