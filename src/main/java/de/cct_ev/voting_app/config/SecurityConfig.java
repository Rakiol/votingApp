package de.cct_ev.voting_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // H2-Konsole und **alle** Actuator-Endpunkte freigeben
                        .requestMatchers("/h2/**", "/actuator/**").permitAll()
                        // alles andere bleibt geschützt
                        .anyRequest().authenticated()
                )
                // CSRF für H2 ausschalten
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2/**"))
                // Frames erlauben für H2 UI
                .headers(h -> h.frameOptions(f -> f.disable()));
        return http.build();
    }
}