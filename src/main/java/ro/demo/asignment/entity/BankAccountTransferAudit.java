package ro.demo.asignment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class BankAccountTransferAudit extends AbstractAuditable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bank_account_source_id")
    private BankAccount bankAccountSource;
    @ManyToOne
    @JoinColumn(name = "bank_account_destination_id")
    private BankAccount bankAccountDestination;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer transferred;
}
