package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.InstallationCreatorDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.service.InstallationService;
import claurendeau.hackqc.algo.Backend.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/installation")
@CrossOrigin(origins = "http://localhost:4200")
public class InstallationController {

    @Autowired
    InstallationService installationService;

    @Autowired
    LocationService locationService;

    @PostMapping("/cree")
    public ResponseEntity<String> createInstallation(@RequestBody InstallationCreatorDTO installationCreatorDTO) {
        try {
            installationService.createInstallation(installationCreatorDTO.name(),
                    installationCreatorDTO.type(),
                    installationCreatorDTO.description(),
                    installationCreatorDTO.locationId());
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
