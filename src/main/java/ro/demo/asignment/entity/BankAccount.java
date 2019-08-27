package ro.demo.asignment.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
