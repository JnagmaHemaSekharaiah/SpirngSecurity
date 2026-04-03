package com.ex.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecuirtyConfig
{


    // @Bean
    public InMemoryUserDetailsManager userDetailService()
    {
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123")) // in spring 6 + we need to encide the password
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

   // @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


 //   @Bean
    public SecurityFilterChain  userDetailSerivce(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(request ->request
                .requestMatchers("/login-check","/contact").hasAnyRole("ADMIN","USER")
                .requestMatchers("/transfer","/balance").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults());

       return http.build();

    }


}
