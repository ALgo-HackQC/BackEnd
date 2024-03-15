package claurendeau.hackqc.algo.Backend.mapper;

import claurendeau.hackqc.algo.Backend.dto.InstallationDTO;
import claurendeau.hackqc.algo.Backend.modeles.Installation;

public class InstallationMapper {
    public static InstallationDTO toInstallationDTO(Installation installation) {
        if (installation == null) return null;
        return new InstallationDTO(installation.getId(), installation.getName(),
                installation.getType(), installation.getDescription(),
                installation.getVotesPour(), installation.getVotesContre());
    }
}
