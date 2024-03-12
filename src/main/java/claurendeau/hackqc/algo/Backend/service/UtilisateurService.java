package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void creeUtilisateur() {
        // TODO
    }
}
