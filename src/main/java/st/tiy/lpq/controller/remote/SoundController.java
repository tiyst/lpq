package st.tiy.lpq.controller.remote;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import st.tiy.lpq.model.remote.riot.champion.champions.Sound;
import st.tiy.lpq.service.remote.SoundService;

import java.io.IOException;

public class SoundController {
	private SoundService soundService;
	public SoundController(SoundService soundService) {
		this.soundService = soundService;
	}
	public Sound[] getChampionData(String championName) throws IOException {
		Elements elements = soundService.getAudioFiles(championName);
		Sound[] audioFiles = new Sound[elements.size()];
		int index = 0;
		for (Element element: elements) {
			Sound sound = new Sound();
			sound.setAudio(element.attr("src"));
			sound.setChampion(element.parent().attributes().toString());
			audioFiles[index++] = sound;
		}
		return audioFiles;
	}
}
