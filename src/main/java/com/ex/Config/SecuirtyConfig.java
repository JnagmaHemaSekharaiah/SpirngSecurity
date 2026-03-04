package com.ex.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuirtyConfig
{

    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception
    {
       //logic to custamize the secrity in our app

        http.authorizeHttpRequests
                ((request)-> request.requestMatchers("/","/login-check","/contact","/swagger-ui.html")
                                .permitAll()
                                .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults()
        );
        return http.build();
    }



}
