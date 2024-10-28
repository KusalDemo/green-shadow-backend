package lk.ijse.green_shadow_backend.util;

import lk.ijse.green_shadow_backend.dto.impl.CropDTO;
import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.impl.Crop;
import lk.ijse.green_shadow_backend.entity.impl.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public User mapToUser(UserDTO userDTO){return modelMapper.map(userDTO, User.class);}
    public UserDTO mapToUserDTO(User user){return modelMapper.map(user, UserDTO.class);}

    public Crop mapToCrop(CropDTO cropDto){return modelMapper.map(cropDto, Crop.class);}
    public CropDTO mapToCropDTO(Crop crop){return modelMapper.map(crop, CropDTO.class);}
    public List<CropDTO> mapToCropDTOList(List<Crop> cropList){return cropList.stream().map(this::mapToCropDTO).toList();}
    public List<Crop> mapToCropList(List<CropDTO> cropDTOList){return cropDTOList.stream().map(this::mapToCrop).toList();}

}
