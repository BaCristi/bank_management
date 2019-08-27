package ro.demo.asignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.demo.asignment.entity.BankAccountDepositAudit;

public interface BankAccountDepositAuditRepository extends JpaRepository<BankAccountDepositAudit, Long> {
}
