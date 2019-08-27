package ro.demo.asignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.demo.asignment.entity.BankAccount;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByUserEmailAndAccountName(final String email, final String accountName);
}
