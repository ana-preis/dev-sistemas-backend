package com.incendiosflorestais.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilters securityFilters;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new ForbiddenHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable().cors().and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/login")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/users")
            .permitAll()
            .requestMatchers(HttpMethod.GET, "/users")
            .permitAll()
            .requestMatchers(HttpMethod.GET , "/fires")
            .permitAll()
            .requestMatchers(HttpMethod.GET , "/images")
            .permitAll()
            .requestMatchers(HttpMethod.GET , "/parks")
            .permitAll()
            .requestMatchers(HttpMethod.POST , "/refresh")
            .permitAll()
            .requestMatchers(HttpMethod.DELETE , "/revoke")
            .permitAll()
            .requestMatchers( "/swagger-ui.html", "/swagger.ui/*" )
            .permitAll()
            .requestMatchers( HttpMethod.GET, "/recover-password*" )
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(securityFilters, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .accessDeniedHandler(getAccessDeniedHandler())
            .authenticationEntryPoint(authEntryPoint)
            .and()
            .build();
    }
}
