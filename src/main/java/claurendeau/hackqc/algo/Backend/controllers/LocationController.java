package claurendeau.hackqc.algo.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id).toString();
    }
}
