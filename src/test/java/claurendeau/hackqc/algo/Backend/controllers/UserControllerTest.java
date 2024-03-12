package claurendeau.hackqc.algo.Backend.controllers;


import claurendeau.hackqc.algo.Backend.dto.UserCreatorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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


    @Test
    public void test_createUser_normal() throws Exception {

        UserCreatorDTO userCreatorDTO = new UserCreatorDTO("Lastname", "Firstname", "email", "password");
        String userCreatorDTOString = om.writeValueAsString(userCreatorDTO);

        mockMvc.perform(post("/utilisateur/cree")
                .contentType("application/json")
                .content(userCreatorDTOString)
        ).andExpect(status().isCreated());
    }


}
