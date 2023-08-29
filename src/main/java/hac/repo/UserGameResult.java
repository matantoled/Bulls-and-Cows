package hac.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * This class is used to store the user's game result in a database.
 * It is used to store the score and the name of the user.
 * It also ensures that the score is a positive number and the name is not empty.
 */
@Entity
public class UserGameResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // the id

    @Min(value = 1, message = "Score must be positive")
    private int score; // the score

    @NotEmpty(message = "Name is mandatory")
    @Pattern(regexp="^[a-zA-Z\\s]*$", message="Name can only contain letters and spaces, and cannot be blank")
    private String name; // the name

    /**
     * Default constructor
     */
    public UserGameResult() {
    }

    /**
     * Constructs a UserGameResult with the given score and name.
     * @param score the score of the game
     * @param name the name of the user
     */
    public UserGameResult(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     * Gets the score of the game.
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the name of the user.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the score of the game.
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
}
