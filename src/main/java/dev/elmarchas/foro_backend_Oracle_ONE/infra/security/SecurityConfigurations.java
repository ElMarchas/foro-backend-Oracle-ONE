package dev.elmarchas.foro_backend_Oracle_ONE.infra.security;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // Original
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    // throws Exception {
    // return httpSecurity.csrf().disable().sessionManagement()
    // .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Le indicamos a
    // Spring el tipo de sesion
    // .and().authorizeRequests()
    // .requestMatchers(HttpMethod.POST, "/login", "/**").permitAll()
    // .requestMatchers(HttpMethod.DELETE).permitAll()
    // .requestMatchers("/**").permitAll()
    // .requestMatchers("/swagger-ui.html", "/v3/api-docs/**",
    // "/swagger-ui/**").permitAll()
    // .anyRequest()
    // .authenticated()
    // .and()
    // // .addFilterBefore(securityFilter,
    // UsernamePasswordAuthenticationFilter.class)
    // .build();
    // }

    // Ejemplo de alura
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // return http.csrf(csrf -> csrf.disable())
    // .sessionManagement(sess ->
    // sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .build();
    // }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
