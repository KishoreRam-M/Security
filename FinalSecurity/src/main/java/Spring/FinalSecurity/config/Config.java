package Spring.FinalSecurity.config;

import Spring.FinalSecurity.repo.UserRepository;
import Spring.FinalSecurity.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

    private final UserService userService;

    public Config(UserService userService) {
        this.userService = userService; // Inject the UserService bean
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disables CSRF protection
                .httpBasic(Customizer.withDefaults()) // Enables HTTP Basic authentication
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // All requests must be authenticated
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Stateless session management

        return http.build(); // Builds and returns the SecurityFilterChain
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder()); // Set the password encoder
        provider.setUserDetailsService(userService); // Set the custom UserService
        return provider; // Return the configured authentication provider
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
