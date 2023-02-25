package st.tiy.lpq.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.service.GameService;

@RestController
@RequestMapping(path = "/game")
public class GameController {

	private final GameService gameService;

	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@PostMapping(path = "/create/{gameType}/{guessType}")
	public Game createGame(HttpServletRequest request, @PathVariable GameType gameType, @PathVariable GuessType guessType) {
		return gameService.addGame(getSessionId(request), gameType, guessType);
	}

	@PostMapping(path = "/create")
	public Game createGame(HttpServletRequest request) {
		return gameService.addGame(getSessionId(request), GameType.GUESS_CHAMPION, GuessType.SPLASH);
	}

	@PostMapping(path = "/get")
	public Game getGame(HttpServletRequest request) {
		return gameService.getGame(getSessionId(request));
	}

	private static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		return session.getId();
	}

}
