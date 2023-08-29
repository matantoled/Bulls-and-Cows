package hac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the main entry point for the Spring Boot application.
 * The @SpringBootApplication annotation enables autoconfiguration and component scanning.
 */
@SpringBootApplication
public class Ex5TemplateApplication {

    /**
     * This is the main method which is used to run the Spring Boot application.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Ex5TemplateApplication.class, args);
    }

}
