package claurendeau.hackqc.algo.Backend.dto;

import java.util.Date;

public record ConnectionDTO(String token, Date expirationDate) {
}
