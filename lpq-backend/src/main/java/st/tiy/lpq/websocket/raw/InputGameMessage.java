package st.tiy.lpq.websocket.raw;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record InputGameMessage(String playerName, String move) implements Serializable {

}
