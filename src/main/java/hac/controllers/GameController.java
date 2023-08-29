package hac.controllers;

import hac.Guesses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to handle the game.
 * It is used to display the game page and to handle the user's guesses.
 * It is also used to start a new game.
 */
@Controller
public class GameController {

    @Autowired
    private Guesses guessesSession;

    /**
     * This method is used to display the game page.
     * @param model model
     * @return index.html
     */
    @GetMapping("/")
    public String index(Model model) {

        boolean missingGuess = false;

        for (int num : guessesSession.getGuesses()) {
            if (num == -1) {
                missingGuess = true;
                break;
            }
        }

        if (missingGuess) {
            guessesSession.setMessage("Please select a number for each field.");
        }

        System.out.println("winning numbers: " + guessesSession.getWinningNumbers());

        model.addAttribute("solution", guessesSession.getWinningNumbers());
        model.addAttribute("numbers", guessesSession.getNumbers());
        model.addAttribute("guessesObj", guessesSession);
        model.addAttribute("message", guessesSession.getMessage());
        model.addAttribute("bullsAndCows", guessesSession.getBullsAndCows());
        model.addAttribute("userScoreHistory", guessesSession.getUserScoreHistory());
        return "index";
    }

    /**
     * This method is used to start a new game.
     * @param model model
     * @return index.html
     */
    @GetMapping("/startNewGame")
    public String startNewGame(Model model) {
        guessesSession.clearGuesses();
        return "redirect:/";
    }

    /**
     * This method is used to handle the user's guesses.
     * It is used to calculate the bulls and cows.
     * @param guesses guesses
     * @param model model
     * @return index.html or WinPage.html
     */
    @PostMapping("/submitResult")
    public String submitResult(Guesses guesses, Model model) {

        boolean missingGuess = false;
        guessesSession.setGuesses(guesses.getGuesses());

        for (int num : guesses.getGuesses()) {
            if (num == -1) {
                missingGuess = true;
                break;
            }
        }

        if (missingGuess) {
            return "redirect:/";
        }
        else {
            guessesSession.setGuessesHistory(guesses.getGuesses());
            guessesSession.setScore(guessesSession.getScore() + 1);
            Map<String, Integer> result = calculateBullsAndCows();
            if (result.get("bulls") == 4) {
                guessesSession.setWin(true);
                return "redirect:/WinPage";
            }
            else {
                guessesSession.setBullsAndCows(new int[]{result.get("bulls"), result.get("cows")});
                guessesSession.setMessage("Your guess: " + result.get("bulls") + " Bulls and " + result.get("cows") + " Cows.");
                return "redirect:/";
            }
        }
    }

    /**
     * This method is used to handle exceptions.
     * @param ex exception
     * @param model model
     * @return error.html
     */
    @ExceptionHandler({Exception.class})
    public String handleValidationExceptions(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    /**
     * This method is used to calculate the bulls and cows.
     * @return Map<String, Integer> result (bulls and cows)
     */
    public Map<String, Integer> calculateBullsAndCows() {
        int bulls = 0;
        int cows = 0;
        int[] cowsNumbers = new int[4];
        Arrays.fill(cowsNumbers, -1);

        int[] userGuess = guessesSession.getGuesses();
        List<Integer> winningNumbers = guessesSession.getWinningNumbers();

        for (int i = 0; i < userGuess.length; i++) {
            if (userGuess[i] == winningNumbers.get(i)) {
                bulls++;
            } else {
                if (winningNumbers.contains(userGuess[i]) &&
                        winningNumbers.get(winningNumbers.indexOf(userGuess[i])) != userGuess[winningNumbers.indexOf(userGuess[i])] &&
                        !contains(cowsNumbers, userGuess[i])) {
                    cows++;
                    cowsNumbers[i] = userGuess[i];
                }
            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("bulls", bulls);
        result.put("cows", cows);
        return result;
    }

    /**
     * This method is used to check if an array contains a value.
     * @param array array
     * @param value value
     * @return boolean (true if the array contains the value, false otherwise)
     */
    public static boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }
}