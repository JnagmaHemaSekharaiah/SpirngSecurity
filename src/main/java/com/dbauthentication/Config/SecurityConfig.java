package com.dbauthentication.Config;

import com.dbauthentication.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
             .csrf(csrf -> csrf.disable())
             .authorizeHttpRequests(auth -> auth

                             .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                             .requestMatchers(HttpMethod.GET,"/auth/user/**").permitAll()
                             .requestMatchers("/contact").permitAll()

             .requestMatchers("/login-check").hasAnyRole("ADMIN","USER")

             .requestMatchers("/balance","/transfer").hasRole("ADMIN")

             .anyRequest().authenticated()
               )
             .httpBasic(Customizer.withDefaults())
             .sessionManagement(session -> session
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             );

      return http.build();
    }


    @Bean
    public PasswordEncoder encoder()
    {
        return  new BCryptPasswordEncoder();
    }

}
