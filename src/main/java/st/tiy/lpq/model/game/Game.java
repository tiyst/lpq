package st.tiy.lpq.model.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String gameCode;
	private boolean isPrivate;

	private GameType gameType;
	private GuessType guessType;

	public Game() {

	}

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
