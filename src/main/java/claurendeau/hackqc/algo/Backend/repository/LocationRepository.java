package claurendeau.hackqc.algo.Backend.repository;

import claurendeau.hackqc.algo.Backend.modeles.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
