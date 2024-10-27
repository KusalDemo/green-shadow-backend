package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.UserDAO;
import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.impl.User;
import lk.ijse.green_shadow_backend.secure.JWTAuthResponse;
import lk.ijse.green_shadow_backend.service.UserService;
import lk.ijse.green_shadow_backend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private Mapping mapper;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    @Override
    public User register(UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        return userDao.save(mapper.mapToUser(userDTO));
    }

    @Override
    public JWTAuthResponse login(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refresh(String accessToken) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User fetchedUser = userDao.findByEmail(email);
        if(fetchedUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return fetchedUser;
    }
}
