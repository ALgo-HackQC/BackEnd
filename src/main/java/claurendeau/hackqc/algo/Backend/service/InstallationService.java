package claurendeau.hackqc.algo.Backend.service;

import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.mapper.InstallationMapper;
import claurendeau.hackqc.algo.Backend.modeles.Installation;
import claurendeau.hackqc.algo.Backend.modeles.Location;
import claurendeau.hackqc.algo.Backend.repository.InstallationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepository installationRepository;

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

}
