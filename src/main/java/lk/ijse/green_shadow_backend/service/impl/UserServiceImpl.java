package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.UserDAO;
import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.entity.Role;
import lk.ijse.green_shadow_backend.entity.impl.User;
import lk.ijse.green_shadow_backend.exception.InvalidUserRoleException;
import lk.ijse.green_shadow_backend.secure.JWTAuthResponse;
import lk.ijse.green_shadow_backend.service.UserService;
import lk.ijse.green_shadow_backend.util.Mapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.InvalidRoleInfoException;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private Mapping mapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @Value("${administrator.clarificationCode}")
    private String adminClarificationCode;
    @Value("${manager.clarificationCode}")
    private String managerClarificationCode;
    @Value("${scientist.clarificationCode}")
    private String scientistClarificationCode;


    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);

    @Override
    public void register(UserDTO userDTO){
        Role role = userDTO.getRole();
        String roleCode = userDTO.getRoleClarificationCode();
        if ((role == Role.ADMINISTRATIVE && roleCode.equals(adminClarificationCode))||
                (role == Role.MANAGER && roleCode.equals(managerClarificationCode))||
                (role == Role.SCIENTIST && roleCode.equals(scientistClarificationCode))){
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userDao.save(mapper.mapToUser(userDTO));
            log.info("User: {} registered successfully with role: {}", userDTO.getEmail(), role);
        }else{
            throw new InvalidUserRoleException("Invalid role or clarification code.");
        }
    }

    @Override
    public JWTAuthResponse login(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword())
        );
        if (auth.isAuthenticated()) {
            log.info("User: {} logged in successfully", userDTO.getEmail());
            return JWTAuthResponse.builder()
                    .token(jwtService.generateToken(userDTO.getEmail()))
                    .build();
        }
        return null;
    }

    @Override
    public JWTAuthResponse refresh(String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);
        User fetchedUser = userDao.findByEmail(userEmail);
        if (fetchedUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        log.info("Token refreshed for user: {}", userEmail);
        return JWTAuthResponse.builder()
                .token(jwtService.generateToken(userEmail))
                .build();
    }
}
