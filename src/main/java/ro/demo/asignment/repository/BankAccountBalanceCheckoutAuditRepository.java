package ro.demo.asignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.demo.asignment.entity.BankAccountBalanceCheckoutAudit;

public interface BankAccountBalanceCheckoutAuditRepository extends JpaRepository<BankAccountBalanceCheckoutAudit, Long> {
}
