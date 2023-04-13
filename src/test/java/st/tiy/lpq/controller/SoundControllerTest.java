package st.tiy.lpq.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.lpq.controller.remote.SoundController;
import st.tiy.lpq.model.remote.riot.champion.champions.Sound;
import st.tiy.lpq.service.remote.SoundService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class SoundControllerTest {
	private static SoundController soundController;

	private static SoundService soundService;

	@BeforeAll
	static void beforeAll() throws IOException {
		soundService = new SoundService();

		soundController = new SoundController(soundService);
	}

	@Test
	void championDataTest() throws IOException {
		Sound[] aatrox = soundController.getChampionData("Aatrox");
		assertNotNull(aatrox, "no files selected");
		assertEquals("not the same", "Aatrox", aatrox[0].getChampion());
	}
}
