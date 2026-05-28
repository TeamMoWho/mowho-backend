package team.mowho.backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private static final List<String> ALLOWED_METHODS =
            List.of("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS");
    private static final List<String> ALLOWED_HEADERS = List.of("*");
    private static final List<String> EXPOSED_HEADERS = List.of("Authorization", "Set-Cookie");
    private static final String[] PERMIT_ALL_PATTERNS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity
    ) throws Exception {
        disableSecurityBasic(httpSecurity);
        configureSessionManagement(httpSecurity);
        configureCorsPolicy(httpSecurity);
        configureApiAuthorization(httpSecurity);

        return httpSecurity.build();
    }

    private void disableSecurityBasic(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
    }

    private void configureSessionManagement(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
    }

    //TODO: CORS 허용 로직 추가
    private void configureCorsPolicy(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(cors -> cors.configurationSource(request -> {
            var corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
            corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
            corsConfiguration.setExposedHeaders(EXPOSED_HEADERS);
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }));
    }

    private void configureApiAuthorization(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .requestMatchers(PERMIT_ALL_PATTERNS).permitAll()
                        .requestMatchers(POST, "/api/members").permitAll()
                        .anyRequest().authenticated()
        );
    }

}
