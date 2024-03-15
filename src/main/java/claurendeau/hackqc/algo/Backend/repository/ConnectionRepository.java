package claurendeau.hackqc.algo.Backend.repository;

import claurendeau.hackqc.algo.Backend.modeles.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, String> {
}
