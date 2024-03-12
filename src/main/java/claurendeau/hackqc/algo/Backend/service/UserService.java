package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.mapper.UserMapper;
import claurendeau.hackqc.algo.Backend.modeles.User;
import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(String lastName, String firstName, String email, String password) {
        if (lastName == null || firstName == null || email == null || password == null
        || lastName.isBlank() || firstName.isBlank() || email.isBlank() || password.isBlank()
        || lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("All fields are required");
        }

        User user = userRepository.save(User.builder()
                .lastName(lastName)
                .firstName(firstName)
                .email(email)
                .passwordHash(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString())
                .build());

        return UserMapper.toUserDTO(user);
    }
}
