package st.tiy.lpq.service.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.model.game.Player;
import st.tiy.lpq.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GameService {

	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public Game addGame(GameType gameType, GuessType guessType) {
		return gameRepository.save(new Game(gameType, guessType));
	}

	public Game connectToGame(String gameId, String playerName, String playerSessionId) {
		Game game = gameRepository.findByGameCode(gameId);

		Player player = new Player(playerSessionId, playerName, game);
		game.addPlayer(player);

		return gameRepository.saveAndFlush(game);
	}

	public Game getGame(String gameCode) {
		return gameRepository.findByGameCode(gameCode);
	}

	public List<Game> getPublicGames() {
		return this.gameRepository.findByIsPublicIsTrue();
	}

	@EventListener
	public void onDisconnect(SessionDisconnectEvent event) {
		log.info("Session {} disconnected", event.getSessionId());

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String playerId = headerAccessor.getSessionId();
		Optional<Game> game = gameRepository.findByPlayerIdsContaining(playerId);
		if (game.isPresent()) {
			Game game1 = game.get();
			List<String> playerIds = game1.getPlayerIds();
			playerIds.remove(playerId);
			if (game1.getPlayers().isEmpty()) {
				log.info("Deleting game {}", game1);
				gameRepository.delete(game1);
				return;
			}
			gameRepository.save(game1);
		}
	}

	public void restartRound(Game game) {
		game.getPlayers().forEach(player -> player.setAnsweredCorrectly(false));
	}

}
