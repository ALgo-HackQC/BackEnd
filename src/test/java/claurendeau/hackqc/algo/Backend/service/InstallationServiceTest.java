package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.InstallationCreatorDTO;
import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationWithInstallationsDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.repository.InstallationRepository;
import claurendeau.hackqc.algo.Backend.repository.LocationRepository;
import org.checkerframework.checker.units.qual.A;
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

    @Autowired
    LocationRepository locationRepository;

    List<InstallationCreatorDTO> createdInstallations = new ArrayList<>();
    List<Long> locationID = new ArrayList<>();

    @BeforeAll
    void initialisation() {

        Long id = locationRepository.save(new Location("parc")).getId();
        Long id2 = locationRepository.save(new Location("parc2")).getId();
        locationID.add(id);
        locationID.add(id2);

        createdInstallations.add(new InstallationCreatorDTO("name " + id, "type", "description", id));
        createdInstallations.add(new InstallationCreatorDTO("name2 " + id, "type2", "description2", id));
        createdInstallations.add(new InstallationCreatorDTO("name3 " + id2, "type3", "description3", id2));

        createdInstallations.forEach((installationCreatorDTO) -> installationService.createInstallation(
                installationCreatorDTO.name(), installationCreatorDTO.type(),
                installationCreatorDTO.description(), installationCreatorDTO.locationId()));
    }

    @Test
    public void test_createInstallation_normal() {
        String name = "name";
        String type = "type";
        String description = "description";
        Long locationId = locationID.get(1);


        InstallationDTO installationDTO = installationService.createInstallation(name, type, description, locationId);

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
        Long locationId = locationID.getFirst();

        assertThrows(IllegalArgumentException.class,
                () -> installationService.createInstallation(name, type, description, locationId));
    }

    @Test
    public void test_createInstallation_valeurVide() {
        String name = "";
        String type = "type";
        String description = "description";
        Long locationId = locationID.getFirst();

        assertThrows(IllegalArgumentException.class,
                () -> installationService.createInstallation(name, type, description, locationId));
    }

    @Test
    public void test_getInstallationsWithLocation_normal() {


        LocationWithInstallationsDTO installationsDTO =
                installationService.getInstallationsWithLocation(createdInstallations.getFirst().locationId());
        System.out.println(installationsDTO);

        System.out.println(installationsDTO.installationDTO());
        assertEquals(2, installationsDTO.installationDTO().size());
    }
}
