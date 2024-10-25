package com.emse.spring.automacorp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public UserDetailsService userDetailsService() {
        // We create a password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("password")).roles(ROLE_USER).build()
        );
        manager.createUser(
                User.withUsername("admin").password(encoder.encode("password")).roles(ROLE_ADMIN).build()
        );
        return manager;
    }


    @Bean
    public org.springframework.security.web.SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/console/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
}