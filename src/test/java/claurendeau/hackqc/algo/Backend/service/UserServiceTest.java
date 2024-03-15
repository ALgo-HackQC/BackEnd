package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.ConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserCreatorDTO;
import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.repository.ConnectionRepository;
import claurendeau.hackqc.algo.Backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConnectionRepository connectionRepository;

    List<UserCreatorDTO> createdUsers = new ArrayList<>();

    @BeforeAll
    void initialisation() {
        createdUsers.add(new UserCreatorDTO("Lastname", "Firstname", "email11111112@email.com", "password"));

        createdUsers.forEach((userCreatorDTO) -> userService.createUser(
                userCreatorDTO.lastName(), userCreatorDTO.firstName(),
                userCreatorDTO.email(), userCreatorDTO.password()));
    }

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

    @Test
    public void test_login_normal() {

        ConnectionDTO connectionDTO = userService.login(createdUsers.getFirst().email(), createdUsers.getFirst().password());

        System.out.println(connectionDTO);
        assertTrue(connectionRepository.existsById(connectionDTO.token()));
    }

    @Test
    public void test_login_erreur_passwordMauvais() {
        assertThrows(EntityNotFoundException.class,
                () -> userService.login(createdUsers.getFirst().email(), "wrong"));
    }

    @Test
    public void test_login_erreur_valeurNullOuVide() {
        assertThrows(IllegalArgumentException.class,
                () -> userService.login(createdUsers.getFirst().email(), ""), "Empty check doesn't work");
        assertThrows(IllegalArgumentException.class,
                () -> userService.login(createdUsers.getFirst().email(), null), "Null check doesn't work");
        assertThrows(IllegalArgumentException.class,
                () -> userService.login(createdUsers.getFirst().email(), "    "), "Blank check doesn't work");
    }

}
