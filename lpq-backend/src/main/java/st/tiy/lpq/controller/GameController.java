package st.tiy.lpq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.model.game.Player;
import st.tiy.lpq.service.game.GameService;
import st.tiy.lpq.websocket.message.input.InputGameMessage;
import st.tiy.lpq.websocket.message.output.PlayerConnectionMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static st.tiy.lpq.websocket.message.output.PlayerConnectionMessage.ConnectionType.CONNECTED;
import static st.tiy.lpq.websocket.message.output.PlayerConnectionMessage.ConnectionType.DISCONNECTED;

@RestController
@RequestMapping(path = "/game")
@Slf4j
@CrossOrigin
public class GameController {

	private final GameService gameService;
	private final SimpMessagingTemplate simp;

	public GameController(GameService gameService, SimpMessagingTemplate simp) {
		this.gameService = gameService;
		this.simp = simp;
	}

	@PostMapping(path = "/create/{gameType}/{guessType}")
	public ResponseEntity<Game> createGame(@PathVariable GameType gameType,
	                                       @PathVariable GuessType guessType) {
		Game game = gameService.addGame(gameType, guessType);

		return ResponseEntity.ok(game);
	}

	@MessageMapping("/lpq/connect/{gameCode}")
	@SendTo("/lpq/game/{gameCode}/players")
	public PlayerConnectionMessage connectToGame(@DestinationVariable String gameCode,
												 @Payload String userName,
												 @Header("simpSessionId") String sessionId) {
		Player player = gameService.connectToGame(gameCode, userName, sessionId);

		return new PlayerConnectionMessage(CONNECTED, player);
	}

	@MessageMapping("/lpq/{gameCode}")
	@SendTo("/lpq/game/{gameCode}")
	public Game sendMessage(@DestinationVariable String gameCode,
							@Payload InputGameMessage message,
							@Header("simpSessionId") String sessionId) {
		Game game = gameService.getGame(gameCode);

		log.info("Message: {}", message.toString());
		log.info("Session ID: {}", sessionId);

		return game;
	}

	@GetMapping(path = "/{gameId}/players")
	@CrossOrigin
	public ResponseEntity<List<Player>> getPlayersByGameId(@PathVariable String gameId) {
		Game game = gameService.getGame(gameId);
		if (game == null) {
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(game.getPlayers());
	}

	@GetMapping(path = "/open")
	public ResponseEntity<List<Game>> openGames() {
		List<Game> publicGames = gameService.getPublicGames();

		return ResponseEntity.ok(publicGames);
	}

	@GetMapping(path = "/gameTypes")
	public ResponseEntity<List<GameType>> getGameTypes() {
		List<GameType> gameTypes = Arrays.stream(GameType.values()).toList();
		return ResponseEntity.ok(gameTypes);
	}

	@GetMapping(path = "/guessTypes")
	public ResponseEntity<List<GuessType>> getGuessTypes() {
		List<GuessType> guessTypes = Arrays.stream(GuessType.values()).toList();
		return ResponseEntity.ok(guessTypes);
	}

	@Async
	@EventListener
	public void onDisconnect(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();
		log.info("Session {} disconnected", sessionId);

		Optional<Player> player = gameService.disconnectPlayerBySessionId(sessionId);
		player.ifPresent(value -> this.simp.convertAndSend("/lpq/player/" + value.getGame().getGameCode() + "/players",
				new PlayerConnectionMessage(DISCONNECTED, value)));
	}
}
