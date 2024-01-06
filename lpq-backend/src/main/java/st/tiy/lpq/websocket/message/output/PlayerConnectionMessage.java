package st.tiy.lpq.websocket.message.output;

import st.tiy.lpq.model.game.Player;

import java.util.List;

public record PlayerConnectionMessage(ConnectionType type, String playerSessionId, String playerName, List<Player> allPlayers) {
	public enum ConnectionType {
		CONNECTED,
		DISCONNECTED
	}
}
