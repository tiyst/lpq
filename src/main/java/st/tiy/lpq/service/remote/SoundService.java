package st.tiy.lpq.service.remote;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

//

@Service
public class SoundService {
	private static final String DEFAULTURL = "https://leagueoflegends.fandom.com/wiki/%s/LoL/Audio";
	private static final String BROWSER = "Mozilla";
	public Elements getAudioFiles(String championName) throws IOException {
		String url = String.format(DEFAULTURL, championName);
		Document doc = Jsoup.connect(url).userAgent(BROWSER).get();
		return doc.select("audio");
	}
}
