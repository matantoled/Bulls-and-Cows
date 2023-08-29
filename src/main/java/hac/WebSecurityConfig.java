package hac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * This class provides configuration for web security.
 * It defines beans for password encoding and user details service.
 */
@Configuration
public class WebSecurityConfig {

    /**
     * Defines a bean for PasswordEncoder that uses BCrypt hashing function.
     * BCrypt is a strong, slow password hashing function designed to be difficult
     * and time-consuming to crack to reduce vulnerability to brute force attacks.
     *
     * @return a new BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines a bean for UserDetailsService that retrieves user details from an
     * in-memory repository.
     * In this case, it sets up two users: "user" with USER role and "admin" with ADMIN role,
     * both with the password "password" encoded using BCrypt.
     *
     * @param bCryptPasswordEncoder the PasswordEncoder to use for password encoding
     * @return a UserDetailsService backed by an in-memory user details repository
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles("ADMIN")
                .build());
        return manager;
    }
}
