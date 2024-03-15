package claurendeau.hackqc.algo.Backend.mapper;

import claurendeau.hackqc.algo.Backend.dto.UserDTO;
import claurendeau.hackqc.algo.Backend.modeles.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(user.getId(), user.getLastName(), user.getFirstName(), user.getEmail());
    }
}
