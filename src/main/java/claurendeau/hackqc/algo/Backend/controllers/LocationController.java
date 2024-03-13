package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import claurendeau.hackqc.algo.Backend.service.LocationService;

@RestController
@RequestMapping(path = "/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        LocationDTO location = locationService.getLocationById(id);
        return new ResponseEntity<>(location, location == null ? org.springframework.http.HttpStatus.NOT_FOUND : org.springframework.http.HttpStatus.OK);
    }
}
