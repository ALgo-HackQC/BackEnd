package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.ConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserCreatorDTO;
import claurendeau.hackqc.algo.Backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utilisateur")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/cree")
    public ResponseEntity<String> createUser(@RequestBody UserCreatorDTO userCreatorDTO) {

        try {
            userService.createUser(userCreatorDTO.lastName(),
                    userCreatorDTO.firstName(),
                    userCreatorDTO.email(),
                    userCreatorDTO.password());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/connexion")
    public ResponseEntity<ConnectionDTO> login(@RequestBody UserConnectionDTO userConnectionDTO) {
        ConnectionDTO token;
        try {
            token = userService.login(userConnectionDTO.email(), userConnectionDTO.password());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
