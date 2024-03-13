package claurendeau.hackqc.algo.Backend.controllers;

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
    public ResponseEntity<String> getLocationById(@PathVariable Long id) {
        String location = locationService.getLocationById(id).toString();
        return new ResponseEntity<>(location, location == null ? org.springframework.http.HttpStatus.NOT_FOUND : org.springframework.http.HttpStatus.OK);
    }
}
