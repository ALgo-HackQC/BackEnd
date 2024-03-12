package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.UserCreatorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utilisateur")
public class UserController {
    @PostMapping("/cree")
    public ResponseEntity<String> createUser(@RequestBody UserCreatorDTO userCreatorDTO) {


        return null;
    }
}
