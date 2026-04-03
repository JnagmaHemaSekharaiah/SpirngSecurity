package com.dbauthentication.Service;

import com.dbauthentication.Repo.UserReposittory;
import com.dbauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserReposittory repo;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
    {
        User user = repo.findByName(name)
                .orElseThrow( () -> new UsernameNotFoundException("User not Found") );

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }


}
