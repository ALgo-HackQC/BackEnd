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

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
