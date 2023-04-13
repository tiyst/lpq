package st.tiy.lpq.controller.remote;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import st.tiy.lpq.model.remote.riot.champion.Sound;
import st.tiy.lpq.service.remote.SoundService;

import java.io.IOException;
public class SoundController {
	private final SoundService soundService;
	public SoundController(SoundService soundService) {
		this.soundService = soundService;
	}

	private Sound translateElementToSound(Element element) {
		Sound sound = new Sound();
		sound.setAudio(element.attr("src"));
		Element quoteElement = element.parent().siblingElements().last();
		if (quoteElement != null) {
			sound.setQuote(quoteElement.text());
		} else {
			sound.setQuote(null);
		}
		Element categotyElement = element.parent().parent().parent().previousElementSibling();
		if (categotyElement != null) {
			sound.setCategory(categotyElement.text());
		} else {
			sound.setCategory(null);
		}
		return sound;
	}
	public Sound[] getChampionData(String championName) throws IOException {
		Elements soundElements = soundService.getAudioFiles(championName);
		Sound[] audioFiles = new Sound[soundElements.size()];
		int index = 0;
		for (Element soundElement: soundElements) {
			audioFiles[index++] = translateElementToSound(soundElement);
		}
		return audioFiles;
	}
}
