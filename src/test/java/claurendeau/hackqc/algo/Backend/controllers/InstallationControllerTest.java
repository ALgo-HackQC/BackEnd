package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.InstallationCreatorDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.service.InstallationService;
import claurendeau.hackqc.algo.Backend.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class InstallationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Autowired
    InstallationService installationService;

    @Autowired
    LocationService locationService;

    List<InstallationCreatorDTO> createdInstallations = new ArrayList<>();

    @BeforeAll
    void initialisation() {
        createdInstallations.add(new InstallationCreatorDTO("name", "type", "description", 1L));

        Location location = LocationMapper.toEntity(locationService.getLocationById(1L));

        createdInstallations.forEach((installationCreatorDTO) -> installationService.createInstallation(
                installationCreatorDTO.name(), installationCreatorDTO.type(),
                installationCreatorDTO.description(), location));
    }

    @Test
    public void test_createInstallation_normal() throws Exception {

        InstallationCreatorDTO installationCreatorDTO = new InstallationCreatorDTO("name", "type", "description", 1L);
        String installationCreatorDTOString = om.writeValueAsString(installationCreatorDTO);

        mockMvc.perform(post("/installation/cree")
                .contentType("application/json")
                .content(installationCreatorDTOString)
        ).andExpect(status().isCreated());
    }

    @Test
    public void test_createInstallation_valeurNull() throws Exception {

        InstallationCreatorDTO installationCreatorDTO = new InstallationCreatorDTO(null, "type", "description", 1L);
        String installationCreatorDTOString = om.writeValueAsString(installationCreatorDTO);

        mockMvc.perform(post("/installation/cree")
                .contentType("application/json")
                .content(installationCreatorDTOString)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void test_createInstallation_valeurVide() throws Exception {

        InstallationCreatorDTO installationCreatorDTO = new InstallationCreatorDTO("", "type", "description", 1L);
        String installationCreatorDTOString = om.writeValueAsString(installationCreatorDTO);

        mockMvc.perform(post("/installation/cree")
                .contentType("application/json")
                .content(installationCreatorDTOString)
        ).andExpect(status().isBadRequest());
    }
}
