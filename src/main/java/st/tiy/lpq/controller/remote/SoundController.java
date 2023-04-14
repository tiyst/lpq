package st.tiy.lpq.controller.remote;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.lpq.model.Sound;
import st.tiy.lpq.service.remote.SoundService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/wiki")
public class SoundController {

	private final SoundService soundService;

	private Sound translateElementToSound(Element element) {
		Sound sound = new Sound();
		sound.setId(UUID.randomUUID());
		sound.setAudio(element.attr("src"));

		if ((element.parent() != null) && (element.parent().siblingElements().last() != null)) {
			sound.setQuote(element.parent().siblingElements().last().text());
		} else sound.setQuote(null);

		if ((element.parent() != null) && (element.parent().parent() != null)
				&& (element.parent().parent().parent() != null)
				&& (element.parent().parent().parent().previousElementSibling() != null)) {
			sound.setCategory(element.parent().parent().parent().previousElementSibling().text());
		} else sound.setCategory(null);
		return sound;
	}

	public SoundController(SoundService soundService) {
		this.soundService = soundService;
	}

	@GetMapping(path = "sound")
	public List<Sound> getChampionData(String championName) throws IOException {
		Elements soundElements = soundService.getAudioFiles(championName);
		return soundElements.stream()
				.map(this::translateElementToSound)
				.toList();
	}
}
