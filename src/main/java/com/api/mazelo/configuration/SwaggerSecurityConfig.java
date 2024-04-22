package com.api.mazelo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import static org.springframework.security.config.Customizer.withDefaults;

@Order(1)
@Configuration
@RequiredArgsConstructor
public class SwaggerSecurityConfig {
//    @Value("${swagger.login.user}")
//    private String userName;
//    @Value("${swagger.login.password}")
//    private String password;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public RequestAttributeSecurityContextRepository getRequestAttributeSecurityContextRepository() {
        return new RequestAttributeSecurityContextRepository();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("mazeloinitiate@2024")
                .password(passwordEncoder.encode("HappyMazelo@123"))
                .roles("ADMIN");

        http.securityMatcher("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());

        return http.build();
    }
}
