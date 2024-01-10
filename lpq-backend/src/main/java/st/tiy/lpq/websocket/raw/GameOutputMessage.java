package st.tiy.lpq.websocket.raw;

import java.io.Serializable;

public record GameOutputMessage(String userName, String message) implements Serializable {
}
