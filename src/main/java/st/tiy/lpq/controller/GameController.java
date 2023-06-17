package st.tiy.lpq.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.service.GameService;

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
	public ResponseEntity<Game> createGame(HttpServletRequest request, @PathVariable GameType gameType, @PathVariable GuessType guessType) {
		Game game = gameService.addGame(gameType, guessType);

		return ResponseEntity.ok(game);
	}

	@MessageMapping("/lpq/{gameCode}")
	@SendTo("/lpq/game/{gameCode}")
	public Game sendMessage(@DestinationVariable String gameCode, String message) {
//		Game game = gameService.getGame(gameCode);
		Game game = new Game(GameType.GUESS_CHAMPION, GuessType.SPLASH);

//		simp.convertAndSend("/lpq/game/", gameCode);
//		simp.convertAndSend("/app/game/" + gameCode, message);
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

//	public ResponseEntity<Game> getGame(HttpServletRequest request) {
//		Game game = gameService.getGame(getSessionId(request));
//		return ResponseEntity.ok(game);
//	}

	private static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		return session.getId();
	}

}
