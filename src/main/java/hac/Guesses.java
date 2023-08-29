package hac;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is used to manage the session.
 * It is used to store the guesses, the history of guesses,
 * the score, the winning numbers, the message, the numbers,
 * the bulls and cows, the user score history and the win.
 */
@Component
public class Guesses implements Serializable {
    private int[] guesses; // the user's guesses
    private final List<int[]> guessHistory; // the history of the user's guesses (for the bulls and cows)
    private int score = 0; // the user's score
    private List<Integer> winningNumbers = generateWinningNumbers(); // the winning numbers
    private String message = ""; // the message to display
    private List<Integer> numbers = IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList()); // the numbers to display
    private final List<int[]> bullsAndCows= new ArrayList<>(); // the bulls and cows
    private final List<UserScoreHistory> userScoreHistory = new ArrayList<>(); // the user score history
    private boolean isWin = false; // if the user won or not


    /**
     * This constructor is used to initialize the guesses and the history of guesses.
     */
    public Guesses() {
        this.guesses = new int[4];
        Arrays.fill(this.guesses, -1);
        this.guessHistory = new ArrayList<>();
    }

    /**
     * This constructor is used to initialize the guesses and the history of guesses.
     * @param guesses the user's guesses
     */
    public Guesses(int[] guesses) {
        this.guesses = guesses;
        this.guessHistory = new ArrayList<>();
    }

    /**
     * This method is used to get the guesses.
     * @return the guesses
     */
    public int[] getGuesses() {
        return guesses;
    }

    /**
     * Sets the current guesses of the user.
     * @param guesses the new guesses of the user
     */
    public void setGuesses(int[] guesses) {
        this.guesses = guesses;
    }

    /**
     * Records the given guesses into the history of guesses.
     * @param guesses the guesses to be recorded
     */
    public void setGuessesHistory(int[] guesses) {
        this.guessHistory.add(guesses.clone());
    }

    /**
     * Returns the history of guesses made by the user.
     * @return the history of guesses
     */
    public List<int[]> getGuessHistory() {
        return guessHistory;
    }

    /**
     * Returns the current score of the user.
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the current score of the user.
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Generates a set of winning numbers for the game.
     * @return the winning numbers
     */
    public List<Integer> generateWinningNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, 4);
    }

    /**
     * Returns the current winning numbers.
     * @return the winning numbers
     */
    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    /**
     * Generates a new set of winning numbers.
     */
    public void setWinningNumbers() {
        this.winningNumbers = generateWinningNumbers();
    }

    /**
     * Resets all the fields to their initial values,
     * effectively starting a new game.
     */
    public void clearGuesses() {
        Arrays.fill(this.guesses, -1);
        this.guessHistory.clear();
        this.score = 0;
        this.winningNumbers = generateWinningNumbers();
        this.message = "";
        this.bullsAndCows.clear();
        this.isWin = false;
    }

    /**
     * Returns the current display message.
     * @return the display message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the display message.
     * @param message the new display message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the current list of numbers for display.
     * @return the list of numbers
     */
    public List<Integer> getNumbers() {
        return numbers;
    }

    /**
     * Sets the list of numbers for display.
     * @param numbers the new list of numbers
     */
    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Adds a new record of bulls and cows counts.
     * @param bulls_And_Cows the new record of bulls and cows
     */
    public void setBullsAndCows(int[] bulls_And_Cows) {
        this.bullsAndCows.add(bulls_And_Cows);
    }

    /**
     * Returns the history of bulls and cows counts.
     * @return the bulls and cows history
     */
    public List<int[]> getBullsAndCows() {
        return bullsAndCows;
    }

    /**
     * Checks if the user has won the game.
     * @return true if the user has won, false otherwise
     */
    public boolean getWin() {
        return isWin;
    }

    /**
     * Sets the win state of the game.
     * @param win the new win state
     */
    public void setWin(boolean win) {
        this.isWin = win;
    }

    /**
     * Returns the history of user scores.
     * @return the user score history
     */
    public List<UserScoreHistory> getUserScoreHistory() {
        return userScoreHistory;
    }

    /**
     * Records a new user score into the history of user scores.
     * @param userScoreHistory the new user score to be recorded
     */
    public void setUserScoreHistory(UserScoreHistory userScoreHistory) {
        this.userScoreHistory.add(userScoreHistory);
    }
}


