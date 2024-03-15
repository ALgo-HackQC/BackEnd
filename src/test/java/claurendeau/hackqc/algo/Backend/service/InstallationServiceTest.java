package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.InstallationCreatorDTO;
import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.repository.InstallationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstallationServiceTest {

    @Autowired
    InstallationService installationService;

    @Autowired
    InstallationRepository installationRepository;

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
    public void test_createInstallation_normal() {
        String name = "name";
        String type = "type";
        String description = "description";
        Long locationId = 1L;

        Location location = LocationMapper.toEntity(locationService.getLocationById(locationId));

        InstallationDTO installationDTO = installationService.createInstallation(name, type, description, location);

        assertTrue(installationRepository.existsById(installationDTO.id()));

        assertEquals(name, installationDTO.name());
        assertEquals(type, installationDTO.type());
        assertEquals(description, installationDTO.description());
    }

    @Test
    public void test_createInstallation_valeurNull() {
        String name = null;
        String type = "type";
        String description = "description";
        Long locationId = 1L;

        Location location = LocationMapper.toEntity(locationService.getLocationById(locationId));

        assertThrows(IllegalArgumentException.class,
                () -> installationService.createInstallation(name, type, description, location));
    }

    @Test
    public void test_createInstallation_valeurVide() {
        String name = "";
        String type = "type";
        String description = "description";
        Long locationId = 1L;

        Location location = LocationMapper.toEntity(locationService.getLocationById(locationId));

        assertThrows(IllegalArgumentException.class,
                () -> installationService.createInstallation(name, type, description, location));
    }
}
