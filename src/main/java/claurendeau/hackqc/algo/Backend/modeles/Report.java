package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "REPORTS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
public class Report {

    enum Severity {
        LOW,
        MEDIUM,
        URGENT
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "SEVERITY")
    @Enumerated
    private Severity severity;

    @Column(name = "PROBLEM")
    private String problem;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    @JoinColumn(name = "INSTALLATION_ID")
    @ManyToOne
    private Installation installation;

    public Report(Date date, Severity severity, String problem, User user, Installation installation) {
        this.date = date;
        this.severity = severity;
        this.problem = problem;
        this.user = user;
        this.installation = installation;
    }
}
