package com.vitaly.firstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = createAdminUser("vitaly", "pass");
        UserDetails user2 = createUser("test", "password");

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createUser(String username, String password) {
        Function<String, String> encoder = (pass) -> passwordEncoder().encode(pass);
        UserDetails userDetails = User
                .builder()
                .passwordEncoder(encoder)
                .username(username)
                .password(password)
                .roles("USER")
                .build();
        return userDetails;
    }

    private UserDetails createAdminUser(String username, String password) {
        Function<String, String> encoder = (pass) -> passwordEncoder().encode(pass);
        UserDetails userDetails = User
                .builder()
                .passwordEncoder(encoder)
                .username(username)
                .password(password)
                .roles("ADMIN", "USER")
                .build();
        return userDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security.authorizeHttpRequests(request -> request.requestMatchers(new AntPathRequestMatcher("/todo-list/**")).hasRole("USER")
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).hasRole("ADMIN")
                .anyRequest().authenticated());
        security.formLogin(Customizer.withDefaults());
        security.csrf(AbstractHttpConfigurer::disable);
        security.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return security.build();
    }

}
