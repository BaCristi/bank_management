package ro.demo.asignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.demo.asignment.entity.BankAccount;
import ro.demo.asignment.entity.User;
import ro.demo.asignment.exception.BankAccountBalanceInsufficientException;
import ro.demo.asignment.exception.BankAccountNotFoundException;
import ro.demo.asignment.model.BankAccountDepositRequest;
import ro.demo.asignment.model.BankAccountResponseModel;
import ro.demo.asignment.model.BankAccountTransferRequest;
import ro.demo.asignment.repository.BankAccountRepository;

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
    @NotNull
    private final BankAccountAuditService bankAccountAuditService;

    @Transactional
    public BankAccountResponseModel createBankAccount(final User savedUser) {
        final BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(savedUser);
        bankAccount.setAccountName(generateUniqueAccountName(savedUser));
        bankAccount.setBalance(0);
        final BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return mapToModel(savedBankAccount);
    }

    public BankAccountResponseModel getBalanceForAccount(final String email, final String accountName) {
        final BankAccount bankAccount = this.getBankAccount(email, accountName);
        bankAccountAuditService.saveCheckoutAudit(bankAccount);
        return mapToModel(bankAccount);
    }

    private BankAccountResponseModel mapToModel(final BankAccount savedBankAccount) {
        return new BankAccountResponseModel(savedBankAccount.getAccountName(), savedBankAccount.getBalance());
    }

    private String generateUniqueAccountName(final User savedUser) {
        return savedUser.getEmail().split("@")[0] + "_" + UUID.randomUUID().toString().substring(0, 7);
    }

    @Transactional
    public BankAccountResponseModel depositFunds(final BankAccountDepositRequest request) {
        return bankAccountRepository
                .findByUserEmailAndAccountName(request.getEmail(), request.getAccountName())
                .map(bankAccount -> {
                    final BankAccount modifiedBankAccount = updateBalance(request, bankAccount);
                    bankAccountAuditService.saveDepositAudit(modifiedBankAccount.getUser(), bankAccount, request.getDepositQuantity());
                    return modifiedBankAccount;
                })
                .map(this::mapToModel)
                .orElseThrow(BankAccountNotFoundException::new);
    }

    private BankAccount updateBalance(final BankAccountDepositRequest request, final BankAccount bankAccount) {
        bankAccount.setBalance(bankAccount.getBalance() + request.getDepositQuantity());
        return bankAccount;
    }

    @Transactional
    public List<BankAccountResponseModel> transferFunds(final BankAccountTransferRequest request) {
        final BankAccount bankAccountSource = getBankAccount(request.getEmail(), request.getAccountNameSource());
        final BankAccount bankAccountDestination = getBankAccount(request.getEmail(), request.getAccountNameDestination());

        final boolean hasInsufficientFunds = bankAccountSource.getBalance() < request.getTransferQuantity();
        if (hasInsufficientFunds) {
            throw new BankAccountBalanceInsufficientException();
        }

        bankAccountDestination.setBalance(bankAccountDestination.getBalance() + request.getTransferQuantity());
        bankAccountSource.setBalance(bankAccountSource.getBalance() - request.getTransferQuantity());

        bankAccountAuditService.saveTransferAudit(bankAccountDestination.getUser(), bankAccountDestination,
                bankAccountSource, request.getTransferQuantity());

        return Arrays.asList(mapToModel(bankAccountSource), mapToModel(bankAccountDestination));
    }

    private BankAccount getBankAccount(final String email, final String accountName) {
        return bankAccountRepository
                .findByUserEmailAndAccountName(email, accountName)
                .orElseThrow(BankAccountNotFoundException::new);
    }
}
