package hac;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * This class is used to configure the beans.
 */
@Configuration
public class MyConfig {


    /**
     * Defines a bean of type Guesses that is session-scoped.
     * This means a new instance of Guesses will be created for each session.
     * The bean is used to manage the game session, storing the user's guesses, score,
     * winning numbers, and other related information.
     *
     * @return a new Guesses instance
     */
    @Bean
    @SessionScope
    public Guesses guessesSession() {
        return new Guesses();
    }
}
