package claurendeau.hackqc.algo.Backend.repository;

import claurendeau.hackqc.algo.Backend.modeles.Installation;
import claurendeau.hackqc.algo.Backend.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallationRepository  extends JpaRepository<Installation, Long> {
}
