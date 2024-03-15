package claurendeau.hackqc.algo.Backend.controllers;

import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import claurendeau.hackqc.algo.Backend.service.LocationService;

@RestController
@RequestMapping(path = "/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        LocationDTO location = locationService.getLocationById(id);
        return new ResponseEntity<>(location, location == null ? org.springframework.http.HttpStatus.NOT_FOUND : org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        return new ResponseEntity<>(locationService.createLocation(locationDTO), org.springframework.http.HttpStatus.CREATED);
    }
}
