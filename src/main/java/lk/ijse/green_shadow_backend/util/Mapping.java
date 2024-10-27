package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.impl.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO){return modelMapper.map(userDTO, User.class);}
    public UserDTO mapToUserDTO(User user){return modelMapper.map(user, UserDTO.class);}
}
