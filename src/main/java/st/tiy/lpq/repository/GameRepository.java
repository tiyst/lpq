package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.game.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	Game findByGameCode(String gameCode);

	List<Game> findByIsPublicIsTrue();

	Optional<Game> findByPlayerIdsContaining(String id);

}
