package com.dbauthentication.Service;

import com.dbauthentication.Repo.UserReposittory;
import com.dbauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserReposittory repo;

    public User save(User user)
    {
        if(repo.findByName(user.getName()).isPresent())
        {
            throw new RuntimeException("User already exsist with name "+ user.getName());
        }

        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole(user.getRole().toUpperCase());

        return repo.save(user);
    }

    public User getUser(Integer id)
    {
        if(!repo.findById(id).isPresent())
        {
            throw new RuntimeException("no user is prsent");
        }
        return repo.findById(id).get();
    }
}
