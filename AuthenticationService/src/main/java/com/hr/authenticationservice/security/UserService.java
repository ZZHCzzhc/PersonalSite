package com.hr.authenticationservice.security;

import com.hr.authenticationservice.dao.Impl.RoleDao;
import com.hr.authenticationservice.dao.Impl.UserDao;
import com.hr.authenticationservice.dao.Impl.UserRoleDao;
import com.hr.authenticationservice.domain.User;
import com.hr.authenticationservice.domain.UserRole;
import com.hr.authenticationservice.domain.response.RegistrationResponse;
import com.hr.authenticationservice.security.AuthUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private RoleDao roleDao;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.loadUserByUsername(username);

        if (!userOptional.isPresent()){
        }

        User user = userOptional.get(); // database user

        return AuthUserDetail.builder() // spring security's userDetail
                .userid(user.getID())
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();
        List<UserRole> UserRoles = userRoleDao.getAllUserRoleS(user.getID());
        List<String> roles = roleDao.getAllUserRoles(UserRoles);

        if (roles == null) throw new RuntimeException("zero Authority");

        for (String role :  roles){
            userAuthorities.add(new SimpleGrantedAuthority(role));
        }

        return userAuthorities;
    }

    public RegistrationResponse create(String username, String password, String email) {
        RegistrationResponse registrationResponse = RegistrationResponse.builder().message("success").build();

        try {
            userDao.create(username, password, email);
        } catch (Exception e) {
            registrationResponse.setMessage(e.getMessage());
        }

        return registrationResponse;
    }

}
