package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "CONTRIBUTIONS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
public class Contribution {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "USER_ID")
    @ManyToOne
    private User user;

    @Column(name = "INSTALLATION_ID")
    @ManyToOne
    private Installation installation;

    public Contribution(Date date, String description, User user, Installation installation) {
        this.date = date;
        this.description = description;
        this.user = user;
        this.installation = installation;
    }
}
