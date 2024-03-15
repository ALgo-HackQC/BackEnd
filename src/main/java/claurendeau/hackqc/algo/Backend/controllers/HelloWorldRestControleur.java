package claurendeau.hackqc.algo.Backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestControleur {
    @GetMapping("/hello")
    public String getHello() {
        return "Salut gang!";
    }

    @PostMapping("/hello")
    public String postHello(@RequestBody String message) {
        return "Message posted: " + message;
    }
}
