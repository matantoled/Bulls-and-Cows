package hac;

/**
 * This class is used to store the user score history.
 * It is used to store the score, the name and the date.
 */
public class UserScoreHistory {
    int score; // the score
    String name; // the name
    String date; // the date

    /**
     * Constructs a new UserScoreHistory with the specified score, name, and date.
     *
     * @param score the score
     * @param name the name
     * @param date the date
     */
    public UserScoreHistory(int score, String name, String date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    /**
     * Default constructor for UserScoreHistory.
     * Initializes score, name, and date to default values.
     */
    public UserScoreHistory() {
        this.score = 0;
        this.name = "";
        this.date = "";
    }

    /**
     * Returns the score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the score.
     *
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
