package com.kdg.springprojt5.security;

import com.kdg.springprojt5.repository.springdata.SpringDataUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SpringDataUserRepository userService;

    public CustomUserDetailsService(SpringDataUserRepository userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByUsername(username);
        if (user != null) {
            var authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getCode()));
            return new CustomUserDetails(user.getUsername(), user.getPassword(),
                    authorities, user.getId());
        }
        throw new UsernameNotFoundException("User '" + username + "' doesn't exist");
    }
}