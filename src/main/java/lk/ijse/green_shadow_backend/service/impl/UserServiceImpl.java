package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.impl.User;
import lk.ijse.green_shadow_backend.secure.JWTAuthResponse;
import lk.ijse.green_shadow_backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User register(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse login(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refresh(String accessToken) {
        return null;
    }
}
