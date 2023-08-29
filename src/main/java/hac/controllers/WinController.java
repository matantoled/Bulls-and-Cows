package hac.controllers;

import hac.Guesses;
import hac.UserScoreHistory;
import hac.repo.UserGameResult;
import hac.repo.UserGameResultRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to handle the win page.
 * It is used to display the win page and to handle the user's name, score and date.
 * It is also used to display the high scores.
 */
@Service
@Controller
public class WinController {

    @Autowired
    private UserGameResultRepository userGameResultRepository;
    @Autowired
    private Guesses guessesSession;


    /**
     * This method is used to display the win page.
     * @param model model
     * @return WinPage.html or index.html
     */
    @GetMapping("/WinPage")
    public String winPage(Model model) {

        if (!guessesSession.getWin()) {
            return "redirect:/";
        }
        else {
            UserGameResult userGameResult = new UserGameResult(guessesSession.getScore(), "");
            model.addAttribute("score", guessesSession.getScore());
            model.addAttribute("userGameResult", userGameResult);
            model.addAttribute("userScoreHistory", guessesSession.getUserScoreHistory());
            return "WinPage";
        }
    }

    /**
     * This method is used to display the high scores.
     * It is also used to save the user's name, score and date.
     * It is also used to update the user's score if it is lower than the one in the database.
     * @param userGameResult userGameResult object that contains the user's name and score
     * @param result result
     * @param model model
     * @return WinPage.html (if not valid) or index.html (if the user try to access this page) or HighScores.html
     */
    @Transactional
    @PostMapping("/highScores")
    public String highScores(@Valid UserGameResult userGameResult, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("score", guessesSession.getScore());
            model.addAttribute("userGameResult", userGameResult);
            model.addAttribute("userScoreHistory", guessesSession.getUserScoreHistory());
            return "WinPage";
        }
        if(guessesSession.getWin()) {
            // checks if the name is already in the database
            if (userGameResultRepository.findByName(userGameResult.getName()).size() > 0) {
                // check if the score is lower than the one in the database
                if (userGameResultRepository.findByName(userGameResult.getName()).get(0).getScore() > guessesSession.getScore()) {
                    // update the score
                    userGameResultRepository.findByName(userGameResult.getName()).get(0).setScore(guessesSession.getScore());
                }
            }
            // else if the name is not in the database but the size of the database is >= 5
            else if (userGameResultRepository.findAll().size() >= 5) {
                // check if the score is lower than the maximum score in the database
                if (userGameResultRepository.findTopByOrderByScoreDesc().getScore() > guessesSession.getScore()) {
                    // delete the maximum score
                    userGameResultRepository.delete(userGameResultRepository.findTopByOrderByScoreDesc());
                    // save the new score
                    userGameResultRepository.save(userGameResult);
                }
            }
            else {
                userGameResultRepository.save(userGameResult);
            }
            //save user score
            guessesSession.setUserScoreHistory(new UserScoreHistory(guessesSession.getScore(), userGameResult.getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
            guessesSession.clearGuesses();
            model.addAttribute("highScores", userGameResultRepository.findAllByOrderByScoreAsc());
            model.addAttribute("userScoreHistory", guessesSession.getUserScoreHistory());
            return "HighScores";
        }   else {
            return "redirect:/";
        }
    }

    /**
     * This method is used to reset the database.
     * @return index.html
     */
    @Transactional
    @PostMapping("/resetDb")
    public String resetDb() {
        userGameResultRepository.deleteAll();
        return "redirect:/";
    }

    /**
     * This method is used to handle the exceptions.
     * @param ex ex
     * @param model model
     * @return error.html
     */
    @ExceptionHandler({Exception.class})
    public String handleValidationExceptions(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
