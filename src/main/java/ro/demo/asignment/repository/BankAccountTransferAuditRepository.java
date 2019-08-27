package ro.demo.asignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.demo.asignment.entity.BankAccountTransferAudit;

public interface BankAccountTransferAuditRepository extends JpaRepository<BankAccountTransferAudit, Long> {
}
