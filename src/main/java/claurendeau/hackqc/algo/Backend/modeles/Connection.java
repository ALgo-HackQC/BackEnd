package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Entity
@Table(name = "CONNECTIONS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder
public class Connection {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ISSUE_DATE")
    private Date issueDate;

    @Column(name = "EXPIRATION")
    private Date expiration;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    public Connection(User user, String token, Date issueDate, Date expiration, Installation installation) {
        this.user = user;
        this.token = token;
        this.issueDate = issueDate;
        this.expiration = expiration;
    }
}
