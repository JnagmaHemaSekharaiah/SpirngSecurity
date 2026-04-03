package com.dbauthentication.controller;

import com.dbauthentication.Service.CustomUserDetailsService;
import com.dbauthentication.Service.UserService;
import com.dbauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private CustomUserDetailsService service;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id)
    {
        return userService.getUser(id);
    }

}
