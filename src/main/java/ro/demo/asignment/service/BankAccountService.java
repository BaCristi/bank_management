package ro.demo.asignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.demo.asignment.entity.BankAccount;
import ro.demo.asignment.entity.BankAccountRepository;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.exception.BankAccountBalanceInsufficientException;
import ro.demo.asignment.exception.BankAccountNotFoundException;
import ro.demo.asignment.model.BankAccountDepositRequest;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.BankAccountTransferRequest;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
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

        return mapToModel(savedBankAccount);
    }

    private BankAccountResponseModel mapToModel(final BankAccount savedBankAccount) {
        return new BankAccountResponseModel(savedBankAccount.getAccountName(), savedBankAccount.getBalance());
    }

    private String generateUniqueAccountName(final User savedUser) {
        return savedUser.getEmail().split("@")[0] + "_" + UUID.randomUUID().toString().substring(0, 7);
    }

    public BankAccountResponseModel getBalanceForAccount(final String email, final String accountName) {
        return bankAccountRepository
                .findByUserEmailAndAccountName(email, accountName)
                .map(this::mapToModel)
                .orElseThrow(BankAccountNotFoundException::new);
    }

    @Transactional
    public BankAccountResponseModel deposit(final BankAccountDepositRequest request) {
        return bankAccountRepository
                .findByUserEmailAndAccountName(request.getEmail(), request.getAccountName())
                .map(bankAccount -> updateBalance(request, bankAccount))
                .map(this::mapToModel)
                .orElseThrow(BankAccountNotFoundException::new);
    }

    private BankAccount updateBalance(final BankAccountDepositRequest request, final BankAccount bankAccount) {
        bankAccount.setBalance(bankAccount.getBalance() + request.getDepositQuantity());
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public List<BankAccountResponseModel> transfer(final BankAccountTransferRequest request) {
        final BankAccount bankAccountSource = bankAccountRepository
                .findByUserEmailAndAccountName(request.getEmail(), request.getAccountNameSource())
                .orElseThrow(BankAccountNotFoundException::new);
        final BankAccount bankAccountDestination = bankAccountRepository
                .findByUserEmailAndAccountName(request.getEmail(), request.getAccountNameDestination())
                .orElseThrow(BankAccountNotFoundException::new);

        if (bankAccountSource.getBalance() < request.getTransferQuantity()) {
            throw new BankAccountBalanceInsufficientException();
        }

        bankAccountDestination.setBalance(bankAccountDestination.getBalance() + request.getTransferQuantity());
        bankAccountSource.setBalance(bankAccountSource.getBalance() - request.getTransferQuantity());

        return Arrays.asList(mapToModel(bankAccountSource), mapToModel(bankAccountDestination));
    }
}
