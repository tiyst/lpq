package st.tiy.lpq.model.game;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

	public static final int DEFAULT_MAX_ROUNDS = 5;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String gameCode;
	private boolean isPublic;

	private GameType gameType;
	private GuessType guessType;
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private List<String> playerIds;

	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Player> players;

	private int currentRound;
	private int maxRounds; //TODO extend database with new entries and make this a non mandatory parameter
	private int roundDuration;

	public Game(GameType gameType, GuessType guessType) {
		this(gameType, guessType, DEFAULT_MAX_ROUNDS);
	}

	public Game(GameType gameType, GuessType guessType, int maxRounds) {
		this.gameType = gameType;
		this.guessType = guessType;
		this.isPublic = true;
		this.playerIds = new ArrayList<>();
		this.players = new ArrayList<>();
		this.maxRounds = maxRounds;
	}

	public boolean addPlayer(Player player) {
		this.playerIds.add(player.getSessionId());
		return this.players.add(player);
	}

	public Optional<Player> removePlayerBySessionId(String sessionId) {
		Optional<Player> player = this.players.stream()
				.filter(p -> sessionId.equals(p.getSessionId()))
				.findFirst();

		if (player.isPresent()) {
			Player playerToRemove = player.get();

			this.playerIds.remove(sessionId);
			this.players.remove(playerToRemove);

			return Optional.of(playerToRemove);
		}

		return Optional.empty();
	}

}
