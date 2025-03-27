package co.edu.uniquindio.proyecto.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import co.edu.uniquindio.proyecto.seguridad.*;


@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE, "/api/reportes/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_MODERADOR") // Clientes y moderadores pueden eliminar
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/login/**","/usuarios","/usuarios/notificacion","/usuarios/Activar", "/api/reportes/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_MODERADOR")  // Solo moderadores
                        .requestMatchers("/usuarios/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_MODERADOR") // Clientes y moderadores
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
