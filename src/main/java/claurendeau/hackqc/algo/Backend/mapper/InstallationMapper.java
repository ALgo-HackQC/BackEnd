package claurendeau.hackqc.algo.Backend.mapper;

import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.modeles.Installation;

public class InstallationMapper {
    public static InstallationDTO toInstallationDTO(Installation installation) {
        return new InstallationDTO(installation.getId(), installation.getName(), installation.getType(), installation.getDescription());
    }
}
