package st.tiy.lpq.service.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import st.tiy.lpq.exception.GameDoesntExistException;
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

		if (game == null) {
			throw new GameDoesntExistException(String.format("Game with ID %s doesn't exist", gameId));
		}

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

	public Optional<Game> disconnectPlayerBySessionId(String sessionId) {
		Optional<Game> optionalGame = gameRepository.findByPlayerIdsContaining(sessionId);

		if (optionalGame.isEmpty()) {
			return Optional.empty();
		}

		Game game = optionalGame.get();
		game.removePlayerBySessionId(sessionId);

		if (game.getPlayerIds().isEmpty()) {
			log.info("Deleting game {}", game.getGameCode());
			gameRepository.delete(game);
			return Optional.empty();
		}
		
		game = gameRepository.saveAndFlush(game);
		return Optional.of(game);
	}

	public void restartRound(Game game) {
		game.getPlayers().forEach(player -> player.setAnsweredCorrectly(false));
	}

	public void startRound(Game game) {
		// TODO: 27/12/2023 return unix timestamp when round ends
		// TODO: 27/12/2023 game round timer 
		// TODO: 27/12/2023 game round shortening after guessing correctly (time adjustment 
	}

}
