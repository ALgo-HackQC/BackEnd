package claurendeau.hackqc.algo.Backend.repository;

import claurendeau.hackqc.algo.Backend.modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<User, Long>{
}
