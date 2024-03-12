package claurendeau.hackqc.algo.Backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utilisateur")
public class UserController {
    @PostMapping("/cree")
    public ResponseEntity<String> createUser() {
        return null;
    }
}
