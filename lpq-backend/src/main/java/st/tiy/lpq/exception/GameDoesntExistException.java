package st.tiy.lpq.exception;

public class GameDoesntExistException extends RuntimeException {

	public GameDoesntExistException() {
		super();
	}

	public GameDoesntExistException(String message) {
		super(message);
	}
}
