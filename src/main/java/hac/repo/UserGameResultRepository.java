package hac.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface is a repository for the UserGameResult entity.
 * It provides methods to interact with the database.
 * It extends JpaRepository which provides JPA related methods like save(), findOne(), findAll(), count(), delete() etc.
 */
public interface UserGameResultRepository extends JpaRepository<UserGameResult, Long> {

    /**
     * This method is used to find all the UserGameResult entities with a specific name.
     * @param userName the name of the user
     * @return a list of UserGameResult entities with the given name
     */
    List<UserGameResult> findByName(String userName);

    /**
     * This method is used to find the UserGameResult entity with the highest score.
     * @return the UserGameResult entity with the highest score
     */
    UserGameResult findTopByOrderByScoreDesc();

    /**
     * This method is used to find all the UserGameResult entities ordered by score in ascending order.
     * @return a list of all UserGameResult entities ordered by score in ascending order
     */
    List<UserGameResult> findAllByOrderByScoreAsc();
}
