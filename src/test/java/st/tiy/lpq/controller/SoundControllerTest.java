package st.tiy.lpq.controller;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import st.tiy.lpq.SoundsWebParser;
import st.tiy.lpq.controller.remote.SoundController;
import st.tiy.lpq.model.game.Game;
import st.tiy.lpq.model.game.GameType;
import st.tiy.lpq.model.game.GuessType;
import st.tiy.lpq.model.remote.riot.champion.champions.Sound;
import st.tiy.lpq.service.GameService;

import javax.swing.text.Element;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public void class SoundControllerTest {
	private static SoundController soundController;

	private static SoundsWebParser soundParser;

	@BeforeAll
	static void beforeAll() throws IOException {
		soundParser = new SoundsWebParser();

		soundController = new SoundController(soundParser);
	}

	@Test
	void championDataTest() throws IOException {
		List<Sound> test = soundController.getChampionData("Bel'Veth");
		assertNotNull(test, "Class has not been created, incorrect champion name");
		Sound sound = test.get(0);
		assertEquals("not same", "BelVeth", sound.getAudio());
	}

	@Test
	void championsDataTest() throws IOException {
		List<Sound> test = soundController.getChampionsData();
		assertNotNull(test, "Class has not been created, incorrect champion name");
		Sound sound = test.get(0);
		assertEquals("not same", "Aatrox", sound.getChampion());
	}
}
