package st.tiy.lpq.websocket.raw;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record ConnectToGameMessage(String gameCode, String userName) implements Serializable {

}
