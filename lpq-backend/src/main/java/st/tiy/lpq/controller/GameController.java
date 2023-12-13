package st.tiy.lpq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.service.game.GameService;
import st.tiy.lpq.websocket.raw.ConnectToGameMessage;
import st.tiy.lpq.websocket.raw.InputGameMessage;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/game")
@Slf4j
public class GameController {

	private final GameService gameService;
	private final SimpMessagingTemplate simp;

	public GameController(GameService gameService, SimpMessagingTemplate simp) {
		this.gameService = gameService;
		this.simp = simp;
	}

	@PostMapping(path = "/create/{gameType}/{guessType}")
	public ResponseEntity<Game> createGame(@PathVariable GameType gameType, @PathVariable GuessType guessType) {
		Game game = gameService.addGame(gameType, guessType);

		return ResponseEntity.ok(game);
	}

	@MessageMapping("/lpq/connect")
	public Game connectToGame(@Payload ConnectToGameMessage connectMessage,
	                          @Header("simpSessionId") String sessionId) {
		return gameService.connectToGame(connectMessage.gameCode(),
		                                 connectMessage.userName(),
		                                 sessionId);
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
}
