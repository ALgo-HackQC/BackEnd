package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "INSTALLATIONS")
@Access(AccessType.FIELD)
@NoArgsConstructor
@Getter
@ToString
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

    public Installation(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
