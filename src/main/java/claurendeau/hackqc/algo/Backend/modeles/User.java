package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

@Entity
@Table(name = "USERS")
@AccessType(AccessType.Type.FIELD)
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String passwordHash;

    public User(String lastName, String firstName, String email, String username, String passwordHash) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
