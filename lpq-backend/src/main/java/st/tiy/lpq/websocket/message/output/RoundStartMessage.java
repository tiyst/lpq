package st.tiy.lpq.websocket.message.output;

import st.tiy.lpq.model.game.Player;

import java.time.LocalDateTime;

public record RoundStartMessage(Player winner, LocalDateTime roundEndTimestamp) {
}
