package st.tiy.lpq.service.remote;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

// https://leagueoflegends.fandom.com/wiki/Aatrox/LoL/Audio

public class SoundService {
	private static final String DEFAULTURL = "https://leagueoflegends.fandom.com/wiki/championName/LoL/Audio";
	private static final String BROWSER = "Mozilla";
	public Elements getAudioFiles(String championName) throws IOException {
		String url = DEFAULTURL.replace("championName",championName);
		Document doc = Jsoup.connect(url).userAgent(BROWSER).get();
		return doc.select("audio");
	}
}
