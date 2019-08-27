package ro.demo.asignment.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Table(name = "users")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String email;

    public User (final String email){
        this.email = email;
    }
}
