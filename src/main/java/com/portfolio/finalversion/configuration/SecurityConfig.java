package com.portfolio.finalversion.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    // TODO revisar como logearme con otro usuario pa
    @Bean
    public InMemoryUserDetailsManager userDetailsServie(){
        UserDetails user = User.withDefaultPasswordEncoder()
                                .username("alexo")
                                .password("123")
                                .roles("USER")
                                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // TODO esta mierda es lo que realiza la validacion
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests((auth) -> auth.requestMatchers("/","/login").permitAll().anyRequest().authenticated()).logout((logOut) -> logOut.permitAll());
        return http.build();
    }
}
