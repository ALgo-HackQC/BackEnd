package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.LocationDTO;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationDTO getLocationById(Long id) {
        return locationRepository.findById(id).map(LocationMapper::toDTO).orElse(null);
    }
}
