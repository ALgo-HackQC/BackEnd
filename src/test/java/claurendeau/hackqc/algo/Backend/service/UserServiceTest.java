package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Test
    public void test_createUser_erreur_valeurNull() {
        String lastName = null;
        String firstName = "Firstname";
        String email = "email@email.com";
        String password = "test";

        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(lastName, firstName, email, password));
    }

    @Test
    public void test_createUser_erreur_valeurVide() {
        String lastName = "";
        String firstName = "Firstname";
        String email = "email@email.com";
        String password = "test";

        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(lastName, firstName, email, password));
    }

}
