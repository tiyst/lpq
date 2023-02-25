package st.tiy.lpq.model.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private GameType gameType;
	private GuessType guessType;

	public Game(GameType gameType, GuessType guessType) {
		this.gameType = gameType;
		this.guessType = guessType;
	}
}
