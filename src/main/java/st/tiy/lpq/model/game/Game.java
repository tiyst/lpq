package st.tiy.lpq.model.game;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

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

	public Game(GameType gameType, GuessType guessType) {
		this.gameType = gameType;
		this.guessType = guessType;
		this.isPublic = true;
		this.playerIds = new ArrayList<>();
		this.players = new ArrayList<>();
	}

	public boolean addPlayer(Player player) {
		this.playerIds.add(player.getSessionId());
		return this.players.add(player);
	}

}
