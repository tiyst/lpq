package st.tiy.lpq.service;

import org.springframework.stereotype.Service;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.repository.GameRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {

	private final Map<String, Game> games;

	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
		this.games = new HashMap<>();
	}

	/**
	 * @return Already existing game || creates a new Game instance
	 */
	public Game addGame(GameType gameType, GuessType guessType) {
		Game game = new Game(gameType, guessType);
		return games.computeIfAbsent(game.getGameCode(), g -> game);
	}

	public Game getGame(String gameCode) {
		return games.get(gameCode);
	}

	public List<Game> getPublicGames() {
		return this.games.values().stream()
		                 .filter(game -> !game.isPrivate())
		                 .toList();
	}
}
