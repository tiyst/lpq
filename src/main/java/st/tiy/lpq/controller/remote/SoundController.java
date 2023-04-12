package st.tiy.lpq.controller.remote;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import st.tiy.lpq.SoundsWebParser;
import st.tiy.lpq.model.remote.riot.champion.champions.Sound;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class SoundController {
	private SoundsWebParser soundWebParser;
	private Element link;

	public SoundController(SoundsWebParser soundWebParser) {
		this.soundWebParser = soundWebParser;
	}

	// Navigates LoL fandom website to champion audio
	private void navigateToChampions() throws IOException {
		link = soundWebParser.getLink("Category:Champion audio"); // Get the Element containing the link
		String relativeHref = link.attr("href"); // Get relative link /wiki/Category:LoL_Champion_audio
		soundWebParser.relativeUrl(relativeHref); //Go to the link
	}
	// Iterates through all the sound elements of a champion
	private List<Sound> getSoundsFromChampion(Element champion) throws IOException {
		List<Sound> sounds = new LinkedList<Sound>();  						// Initiate list of sounds
		String relativeHref = champion.attr("href"); // == "/"	// get relative link out of element
		if (relativeHref.contains("%27")) { 								// ' gets encoded before it is supposed to
			relativeHref = relativeHref.replace("%27","'"); // decoding it back to ' from %27
		}
		soundWebParser.relativeUrl(relativeHref); 							// going to the link
		Elements soundsRaw = soundWebParser.getLinks("File:");		// gathering all audio files
		for (Element soundRaw: soundsRaw) {
			sounds.add(initializeSoundClass(soundRaw));						// initializing all files as sound class
		}
		return sounds;
	}

	// formats elements from HTML element to Sound class
	private Sound initializeSoundClass(Element element) {
		Sound sound = new Sound();
		String arr[] = element.text().split(" ", 2);				// Spliting champion name and the rest of the file
		sound.setChampion(arr[0].substring(5));					// removing "file:" from file name to get champion
		if (arr.length > 1) {
			sound.setType(arr[1]);											// setting the type
		} else {
			sound.setType("none");											// In case there is no type
		}
		sound.setAudio(element.attr("href")); // == "/")			// setting the link as audio
		return sound;
	}

	// Gets the audio data of all champions
	public List<Sound> getChampionsData() throws IOException {
		List<Sound> sounds = new LinkedList<Sound>();
		navigateToChampions();
		Elements champions = soundWebParser.getLinks("Category:");	// Getting elements with all champion audio links
		for (Element champion: champions) {
			sounds.addAll(getSoundsFromChampion(champion));					// Making a list of all audio files across all champions
		}
		soundWebParser.resetUrl();
		return sounds;
	}

	// Gets the audio data of a single champion
	public List<Sound> getChampionData(String championName) throws IOException {
		navigateToChampions();
		link = soundWebParser.getLink("Category:" + championName + " voice-overs");		// Getting a link of a specific champion
		if (link == null) {
			return null;													// If there is a wrong champion name, we return null
		}
		List<Sound> sounds = getSoundsFromChampion(link);					// Getting all the audio files from that champion
		soundWebParser.resetUrl();											// Going back to the main page
		return sounds;
	}
}
