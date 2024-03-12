package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "USERS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
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
