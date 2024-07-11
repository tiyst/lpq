package st.tiy.lpq.websocket.message.output;

import st.tiy.lpq.model.game.Player;

public record PlayerConnectionMessage(ConnectionType type, Player player) {
	public enum ConnectionType {
		CONNECTED,
		DISCONNECTED
	}
}
