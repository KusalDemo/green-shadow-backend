package lk.ijse.green_shadow_backend.service.impl;

import lk.ijse.green_shadow_backend.dao.UserDAO;
import lk.ijse.green_shadow_backend.entity.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDao;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User fetchedUser = userDao.findByEmail(email);
        if(fetchedUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return fetchedUser;
    }
}
