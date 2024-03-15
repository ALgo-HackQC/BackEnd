package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationWithInstallationsDTO;
import claurendeau.hackqc.algo.Backend.service.InstallationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import claurendeau.hackqc.algo.Backend.service.LocationService;

import java.util.List;

@RestController
@RequestMapping(path = "/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

    @Autowired
    LocationService locationService;

     @Autowired
    InstallationService installationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationWithInstallationsDTO> getLocationById(@PathVariable Long id) {
        LocationWithInstallationsDTO locationWithInstallationsDTO;
        try {
            locationWithInstallationsDTO =
                    installationService.getInstallationsWithLocation(id);
        }
        catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(locationWithInstallationsDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        return new ResponseEntity<>(locationService.createLocation(locationDTO), HttpStatus.CREATED);
    }
}
