package claurendeau.hackqc.algo.Backend.modeles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.AccessType;

@Entity
@Table(name = "LOCATIONS")
@AccessType(AccessType.Type.FIELD)
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Location(String name) {
        this.name = name;
    }
}
