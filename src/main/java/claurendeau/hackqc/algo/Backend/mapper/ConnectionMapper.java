package claurendeau.hackqc.algo.Backend.mapper;

import claurendeau.hackqc.algo.Backend.dto.ConnectionDTO;
import claurendeau.hackqc.algo.Backend.modeles.Connection;

public class ConnectionMapper {
    public static ConnectionDTO toConnectionDTO(Connection connection) {
        return new ConnectionDTO(connection.getToken(), connection.getExpiration());
    }
}
