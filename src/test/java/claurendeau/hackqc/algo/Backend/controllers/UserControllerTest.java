package claurendeau.hackqc.algo.Backend.controllers;


import claurendeau.hackqc.algo.Backend.dto.ConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserConnectionDTO;
import claurendeau.hackqc.algo.Backend.dto.UserCreatorDTO;
import claurendeau.hackqc.algo.Backend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.dialect.SpannerSqlAstTranslator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;
    
    @Autowired
    UserService userService;


    List<UserCreatorDTO> createdUsers = new ArrayList<>();

    @BeforeAll
    void initialisation() {
        createdUsers.add(new UserCreatorDTO("Lastname", "Firstname", "email123@email.com", "password"));

        createdUsers.forEach((userCreatorDTO) -> userService.createUser(
                userCreatorDTO.lastName(), userCreatorDTO.firstName(),
                userCreatorDTO.email(), userCreatorDTO.password()));
    }

    @Test
    @Order(1)
    public void test_createUser_normal() throws Exception {

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO("Lastname", "Firstname", "email", "password");
        String userCreatorDTOString = om.writeValueAsString(userCreatorDTO);

        mockMvc.perform(post("/utilisateur/cree")
                .contentType("application/json")
                .content(userCreatorDTOString)
        ).andExpect(status().isCreated());
    }

    @Test
    public void test_createUser_erreurBadRequest_valeurNull() throws Exception {

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO(null, "Firstname", "email", "password");
        String userCreatorDTOString = om.writeValueAsString(userCreatorDTO);

        mockMvc.perform(post("/utilisateur/cree")
                .contentType("application/json")
                .content(userCreatorDTOString)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void test_createUser_erreurBadRequest_valeurVide() throws Exception {

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO("", "Firstname", "email", "password");
        String userCreatorDTOString = om.writeValueAsString(userCreatorDTO);

        mockMvc.perform(post("/utilisateur/cree")
                .contentType("application/json")
                .content(userCreatorDTOString)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void test_createUser_erreurBadRequest_valeurBlanche() throws Exception {

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO("", "Firstname", "email", "password");
        String userCreatorDTOString = om.writeValueAsString(userCreatorDTO);

        mockMvc.perform(post("/utilisateur/cree")
                .contentType("application/json")
                .content(userCreatorDTOString)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @Order(2)
    public void test_login_normal() throws Exception {
        String email = "test11111@email.com";
        String password = "Password";

        UserConnectionDTO userConnectionDTO = new UserConnectionDTO(
                createdUsers.getFirst().email(), createdUsers.getFirst().password());
        String userConnectionDTOString = om.writeValueAsString(userConnectionDTO);

        mockMvc.perform(post("/utilisateur/connexion")
                .contentType("application/json")
                .content(userConnectionDTOString)
        ).andExpect(status().isCreated());
    }

    @Test
    public void test_login_errorNotFound_mauvaisMDP() throws Exception {
        String email = "test11111@email.com";
        String password = "Passwd";

        UserConnectionDTO userConnectionDTO = new UserConnectionDTO(email, password);
        String userConnectionDTOString = om.writeValueAsString(userConnectionDTO);

        mockMvc.perform(post("/utilisateur/connexion")
                .contentType("application/json")
                .content(userConnectionDTOString)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void test_login_errorBadReqest_emailVide() throws Exception {
        String email = "";
        String password = "Passwd";

        UserConnectionDTO userConnectionDTO = new UserConnectionDTO(email, password);
        String userConnectionDTOString = om.writeValueAsString(userConnectionDTO);

        mockMvc.perform(post("/utilisateur/connexion")
                .contentType("application/json")
                .content(userConnectionDTOString)
        ).andExpect(status().isBadRequest());
    }

}
