package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.InstallationCreatorDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.service.InstallationService;
import claurendeau.hackqc.algo.Backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/installation")
public class InstallationController {

    @Autowired
    InstallationService installationService;

    @Autowired
    LocationService locationService;

    @PostMapping("/cree")
    public ResponseEntity<String> createInstallation(@RequestBody InstallationCreatorDTO installationCreatorDTO) {
        LocationDTO locationDTO = locationService.getLocationById(installationCreatorDTO.locationId());
        try {
            if (locationDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Location location = LocationMapper.toEntity(locationDTO);
            installationService.createInstallation(installationCreatorDTO.name(),
                    installationCreatorDTO.type(),
                    installationCreatorDTO.description(),
                    location);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
