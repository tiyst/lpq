package st.tiy.lpq.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import st.tiy.lpq.controller.remote.SoundController;
import st.tiy.lpq.model.remote.riot.champion.Sound;
import st.tiy.lpq.service.remote.SoundService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class SoundControllerTest {
	private static SoundController soundController;

	private static SoundService soundService;

	@BeforeAll
	static void beforeAll() {
		soundService = new SoundService();

		soundController = new SoundController(soundService);
	}

	@Test
	void AatroxChampionData() throws IOException {
		Sound[] aatrox = soundController.getChampionData("Aatrox");
		assertNotNull(aatrox, "no files selected");
		assertEquals("not the same", "https://static.wikia.nocookie.net/leagueoflegends/images/4/4a/Aatrox_Select.ogg/revision/latest?cb=20180623211027", aatrox[0].getAudio());
		assertEquals("Wrong sound quote", "\"Now, hear the silence of annihilation!\"", aatrox[0].getQuote());
		assertEquals("Wrong sound category", "Pick", aatrox[0].getCategory());
	}
	@Test
	void BelVethChampionData() throws IOException {
		Sound[] aatrox = soundController.getChampionData("Bel'Veth");
		assertNotNull(aatrox, "no files selected");
		assertEquals("Wrong sound quote", "\"I am the voice of the silence.\"", aatrox[0].getQuote());
		assertEquals("Wrong sound category", "Pick", aatrox[0].getCategory());
	}
}
