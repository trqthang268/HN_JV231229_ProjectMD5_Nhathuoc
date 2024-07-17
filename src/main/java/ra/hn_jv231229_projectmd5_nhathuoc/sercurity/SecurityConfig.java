package ra.hn_jv231229_projectmd5_nhathuoc.sercurity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.exception.AccessDenied;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.exception.JwtEntryPoint;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.jwt.JwtTokenFilter;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal.UserDetailCustomService;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailCustomService userDetailCustomService;
    private final JwtEntryPoint jwtEntryPoint;
    private final AccessDenied accessDenied;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(config -> config.configurationSource(request -> {
                    CorsConfiguration cf = new CorsConfiguration();
                    cf.setAllowedOrigins(List.of("http://localhost:5173/"));
                    cf.setAllowedMethods(List.of("*"));
                    cf.setAllowCredentials(true);
                    cf.setAllowedHeaders(List.of("*"));
                    cf.setExposedHeaders(List.of("*"));
                    return cf;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        url->url.requestMatchers("/api.myservice.com/v1/admin/**").hasAuthority(RoleName.ROLE_ADMIN.name())
                                .requestMatchers("/api.myservice.com/v1/manager/**").hasAuthority(RoleName.ROLE_MANAGER.name())
                                .requestMatchers("/api.myservice.com/v1/user/**").hasAuthority(RoleName.ROLE_USER.name())
                                .anyRequest().permitAll()
                ).authenticationProvider(authenticationProvider())
                .exceptionHandling(
                        exception -> exception.accessDeniedHandler(accessDenied)
                                .authenticationEntryPoint(jwtEntryPoint)
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailCustomService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
