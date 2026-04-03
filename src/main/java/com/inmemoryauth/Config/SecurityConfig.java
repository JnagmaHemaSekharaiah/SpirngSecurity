package com.inmemoryauth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests
                        (req -> req.requestMatchers("/contact").permitAll()
                        .requestMatchers("/login-check").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/balance","/transfer").hasRole("ADMIN")
                        )
                .httpBasic(Customizer.withDefaults()); // enabling Basic authentication
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails user = User.withUsername("Hema")
                .password(encoder.encode("Sekhar@01")) // no en-coding
                .roles("USER","Admin")
                .build();
        UserDetails admin = User.withUsername("Ravi")
                .password("{noop}Ravi@01")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }



}
