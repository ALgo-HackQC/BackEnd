package claurendeau.hackqc.algo.Backend.dto;

import java.util.List;

public record LocationWithInstallationsDTO(LocationDTO locationDTO, List<InstallationDTO> installationDTO){
}
