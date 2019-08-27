package ro.demo.asignment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    private Integer balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
