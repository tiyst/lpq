package st.tiy.lpq.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.service.game.GameService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static st.tiy.lpq.model.game.GameType.GUESS_SKIN;
import static st.tiy.lpq.model.game.GuessType.ICON;

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
		when(gameService.addGame(GUESS_SKIN, ICON)).thenReturn(buildGame(GUESS_SKIN, ICON));

		Game game = gameController.createGame(GUESS_SKIN, ICON).getBody();

		verify(gameService).addGame(GUESS_SKIN, ICON);
		assertThat(game).extracting(Game::getGameType, Game::getGuessType)
						.containsExactly(GUESS_SKIN, ICON);
	}

	static Game buildGame(GameType gameType, GuessType guessType) {
		return new Game(gameType, guessType);
	}
}