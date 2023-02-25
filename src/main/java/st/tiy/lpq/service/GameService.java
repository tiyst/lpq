package st.tiy.lpq.service;

import org.springframework.stereotype.Service;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

	private final Map<String, Game> games;

	public GameService() {
		this.games = new HashMap<>();
	}

	/**
	 * @return Already existing game || creates a new Game instance
	 */
	public Game addGame(String sessionId, GameType gameType, GuessType guessType) {
		return games.computeIfAbsent(sessionId, g -> new Game(gameType, guessType));
	}

	public Game getGame(String sessionId) {
		return games.get(sessionId);
	}
}
