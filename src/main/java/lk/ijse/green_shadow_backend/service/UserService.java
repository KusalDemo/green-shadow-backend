package lk.ijse.green_shadow_backend.service;

import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.impl.User;
import lk.ijse.green_shadow_backend.secure.JWTAuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.relation.InvalidRoleInfoException;

public interface UserService {
    User register(UserDTO userDTO) throws InvalidRoleInfoException;
    JWTAuthResponse login(UserDTO userDTO);
    JWTAuthResponse refresh(String accessToken);
}
