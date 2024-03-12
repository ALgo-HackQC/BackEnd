package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    public void test_createUser_normal() {
        String lastName = "Lastname";
        String firstName = "Firstname";
        String email = "email@email.com";
        String password = "test";

        UserDTO userDTO = userService.createUser(lastName, firstName, email, password);

        assertTrue(userRepository.existsById(userDTO.id()));

        assertEquals(lastName, userDTO.lastName());
        assertEquals(firstName, userDTO.firstName());
        assertEquals(email, userDTO.email());
    }

}
