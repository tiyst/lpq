package st.tiy.lpq.model.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	@Id
	private String sessionId;

	private String name;
	private int score;

	@Setter
	private boolean answeredCorrectly;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Game game;

	public Player(String sessionId, String name, Game game) {
		this.sessionId = sessionId;
		this.name = name;
		this.score = 0;
		this.game = game;
		this.answeredCorrectly = false;
	}
}
