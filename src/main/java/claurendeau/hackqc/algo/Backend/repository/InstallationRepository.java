package claurendeau.hackqc.algo.Backend.repository;

import claurendeau.hackqc.algo.Backend.modeles.Installation;
import claurendeau.hackqc.algo.Backend.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallationRepository  extends JpaRepository<Installation, Long> {


    @Query("SELECT i FROM Installation i WHERE i.location.id = ?1")
    List<Installation> findAllByLocationId(Long locationId);
}
