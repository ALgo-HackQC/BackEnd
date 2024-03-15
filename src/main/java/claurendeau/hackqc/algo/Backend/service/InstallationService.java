package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.dto.LocationWithInstallationsDTO;
import claurendeau.hackqc.algo.Backend.mapper.InstallationMapper;
import claurendeau.hackqc.algo.Backend.mapper.LocationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Installation;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.repository.InstallationRepository;
import claurendeau.hackqc.algo.Backend.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    LocationRepository locationRepository;


    public InstallationDTO createInstallation(String name, String type, String description, Location location) {
        if (name == null || type == null || description == null || location == null
        || name.isBlank() || type.isBlank() || description.isBlank()){
            throw new IllegalArgumentException("All fields are required");
        }

        Installation installation = installationRepository.save(Installation.builder()
                .name(name)
                .type(type)
                .description(description)
                .location(location)
                .build());

        return InstallationMapper.toInstallationDTO(installation);
    }

    public LocationWithInstallationsDTO getInstallationsWithLocation(Long locationId) {

        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isEmpty()) {
            throw new EntityNotFoundException("Location ID doesn't exist");
        }

        List<Installation> installationList = installationRepository.findAllByLocationId(locationId);

        List<InstallationDTO> installationDTOS = installationList.stream().map(InstallationMapper::toInstallationDTO).toList();

        return new LocationWithInstallationsDTO(location.map(LocationMapper::toDTO).get(), installationDTOS);
    }

}
