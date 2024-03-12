package claurendeau.hackqc.algo.Backend.controleurs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utilisateur")
public class UtilisateurControleur {
    @PostMapping("/cree")
    public ResponseEntity<String> creeUtilisateur() {
        return null;
    }
}
