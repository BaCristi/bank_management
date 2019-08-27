package ro.demo.asignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.demo.asignment.entity.*;
import ro.demo.asignment.repository.BankAccountBalanceCheckoutAuditRepository;
import ro.demo.asignment.repository.BankAccountDepositAuditRepository;
import ro.demo.asignment.repository.BankAccountTransferAuditRepository;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class BankAccountAuditService {
    @NotNull
    private final BankAccountDepositAuditRepository bankAccountDepositAuditRepository;
    @NotNull
    private final BankAccountTransferAuditRepository bankAccountTransferAuditRepository;
    @NotNull
    private final BankAccountBalanceCheckoutAuditRepository bankAccountBalanceCheckoutAuditRepository;

    public void saveDepositAudit(final User user, final BankAccount bankAccount, final Integer deposited) {
        final BankAccountDepositAudit bankAccountDepositAudit = new BankAccountDepositAudit();
        bankAccountDepositAudit.setUser(user);
        bankAccountDepositAudit.setBankAccount(bankAccount);
        bankAccountDepositAudit.setDeposited(deposited);
        bankAccountDepositAuditRepository.save(bankAccountDepositAudit);
    }

    public void saveTransferAudit(final User user, final BankAccount bankAccountDestination,
                                  final BankAccount bankAccountSource, final Integer transferQuantity) {
        final BankAccountTransferAudit bankAccountTransferAudit = new BankAccountTransferAudit();
        bankAccountTransferAudit.setUser(user);
        bankAccountTransferAudit.setBankAccountSource(bankAccountSource);
        bankAccountTransferAudit.setBankAccountDestination(bankAccountDestination);
        bankAccountTransferAudit.setTransferred(transferQuantity);
        bankAccountTransferAuditRepository.save(bankAccountTransferAudit);
    }

    public void saveCheckoutAudit(final BankAccount bankAccount) {
        final BankAccountBalanceCheckoutAudit bankAccountDepositAudit = new BankAccountBalanceCheckoutAudit();
        bankAccountDepositAudit.setUser(bankAccount.getUser());
        bankAccountDepositAudit.setBankAccount(bankAccount);
        bankAccountDepositAudit.setBalance(bankAccount.getBalance());
        bankAccountBalanceCheckoutAuditRepository.save(bankAccountDepositAudit);
    }
}
