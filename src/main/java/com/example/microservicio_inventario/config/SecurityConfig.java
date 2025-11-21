package com.example.microservicio_inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Necesario para HttpMethod.GET
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF para APIs REST
            .csrf(AbstractHttpConfigurer::disable)
            
            // Reglas de autorización
            .authorizeHttpRequests(authorize -> authorize
                
                // Permite acceso libre de lectura (GET) a todos los catálogos
                .requestMatchers(HttpMethod.GET, "/v1/productos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/categorias/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/inventario/**").permitAll()

                // OTRAS CONFIGURACIONES DE ENTORNO (ej. Swagger/actuator si existieran)
                .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                
                // Todas las demás peticiones (POST, PUT, DELETE) requieren autenticación
                .anyRequest().authenticated()
            )
            
            // Usa autenticación HTTP básica (usuario/contraseña)
            .httpBasic(withDefaults());

        return http.build();
    }
}