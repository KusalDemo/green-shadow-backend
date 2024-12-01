package lk.ijse.green_shadow_backend.controller;

import lk.ijse.green_shadow_backend.dto.impl.UserDTO;
import lk.ijse.green_shadow_backend.dto.impl.UserPasswordRenewRequestDTO;
import lk.ijse.green_shadow_backend.secure.JWTAuthResponse;
import lk.ijse.green_shadow_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.InvalidRoleInfoException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO){
        userService.register(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.login(userDTO));
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<JWTAuthResponse> refresh(@RequestParam String accessToken) {
        return ResponseEntity.ok(userService.refresh(accessToken));
    }

    @PutMapping(value = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody UserPasswordRenewRequestDTO renewRequestDTO) {
        userService.updatePassword(renewRequestDTO.getUserDTO(), renewRequestDTO.getNewPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUserRole(@RequestBody UserDTO userDTO) {
        userService.updateUserRole(userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody UserDTO userDTO) {
        userService.delete(userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(value = "/get-role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> getUserRole(@RequestBody UserDTO userDTO){
        String userRole = userService.getUserRole(userDTO);
        System.out.println("UserRole : "+userRole);
        return ResponseEntity.ok(Map.of("userRole",userRole));
    }
}
