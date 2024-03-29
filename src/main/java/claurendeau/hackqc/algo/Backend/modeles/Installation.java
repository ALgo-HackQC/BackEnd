package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "INSTALLATIONS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder
public class Installation {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne
    private Location location;

    @Column(name = "VOTES_FOR")
    private int votesPour;

    @Column(name = "VOTES_AGAINST")
    private int votesContre;

    public Installation(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
