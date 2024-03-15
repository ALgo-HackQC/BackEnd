package claurendeau.hackqc.algo.Backend.dto;

public record InstallationDTO(Long id, String name,
                              String type, String description,
                              Long votePour, Long voteContre) {
}
