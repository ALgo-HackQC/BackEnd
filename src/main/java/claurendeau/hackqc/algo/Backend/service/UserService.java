package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.ConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.mapper.ConnectionMapper;
import claurendeau.hackqc.algo.Backend.mapper.UserMapper;
import claurendeau.hackqc.algo.Backend.modeles.Connection;
import claurendeau.hackqc.algo.Backend.modeles.User;
import claurendeau.hackqc.algo.Backend.repository.ConnectionRepository;
import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import com.google.common.hash.Hashing;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.sql.Date;
import java.time.temporal.ChronoUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

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

    @Transactional
    public ConnectionDTO login(String email, String password) {
        if (email == null || password == null
            || email.isEmpty() || password.isEmpty()
            || email.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("All fields are required");
        }

        String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        User user = userRepository.findByEmailAndPasswordHash(email, passwordHash)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Connection connection = new Connection().builder()
                .user(user)
                .issueDate((Date) Date.from(Instant.now()))
                .expiration((Date) Date.from(Instant.now().plus(
                        Connection.TOKEN_TIME_TO_LIVE_HOURS,
                        ChronoUnit.HOURS)))
                .build();

        return ConnectionMapper.toConnectionDTO(connectionRepository.save(connection));
    }
}
