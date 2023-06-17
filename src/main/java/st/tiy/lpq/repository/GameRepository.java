package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import st.tiy.lpq.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Game findByGameCode(String gameCode);

}
