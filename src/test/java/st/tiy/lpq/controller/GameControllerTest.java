package st.tiy.lpq.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.service.GameService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GameControllerTest {

	private static GameController gameController;
	private static SimpMessagingTemplate simpMessagingTemplate;
	private static GameService gameService;

	@BeforeAll
	static void beforeAll() {
		gameService = mock(GameService.class);
		simpMessagingTemplate = mock(SimpMessagingTemplate.class);

		gameController = new GameController(gameService, simpMessagingTemplate);
	}

	@Test
	void createParametrizedGame() {
		when(gameService.addGame(any(String.class), eq(GameType.GUESS_SKIN), eq(GuessType.ICON)))
				.thenReturn(buildGame(GameType.GUESS_SKIN, GuessType.ICON));
		Game game = gameController.createGame(new MockHttpServletRequest(), GameType.GUESS_SKIN, GuessType.ICON).getBody();

		assertThat(game.getGameType()).isEqualTo(GameType.GUESS_SKIN);
		assertThat(game.getGuessType()).isEqualTo(GuessType.ICON);
	}

	static Game buildGame(GameType gameType, GuessType guessType) {
		return new Game(gameType, guessType);
	}
}