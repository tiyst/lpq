package st.tiy.lpq.service.remote;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

// https://leagueoflegends.fandom.com/wiki/Aatrox/LoL/Audio

public class SoundService {
	private final String defaultUrl = "https://leagueoflegends.fandom.com/wiki/championName/LoL/Audio";
	private final String browser = "Mozilla";
	public Elements getAudioFiles(String championName) throws IOException {
		String url = defaultUrl.replace("championName",championName);
		Document doc = Jsoup.connect(url).userAgent(browser).get();
		Elements audioFiles = doc.select("audio");
		return audioFiles;
	}
}
