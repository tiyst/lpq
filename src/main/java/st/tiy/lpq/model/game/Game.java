package st.tiy.lpq.model.game;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Game {

	private String gameCode;
	private boolean isPrivate;

	private GameType gameType;
	private GuessType guessType;

	public Game(GameType gameType, GuessType guessType) {
		this.gameType = gameType;
		this.guessType = guessType;
		this.gameCode = generateUniqueGameCode();
		this.isPrivate = false;
	}

	private static String generateUniqueGameCode() {
		return UUID.randomUUID().toString();
	}

}
