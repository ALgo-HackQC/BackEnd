package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser() {
        // TODO
    }
}
