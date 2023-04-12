package st.tiy.lpq.model.remote.riot.champion.champions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Sound {
	private String champion;
	private String type;
	private String version;
	private String audio;

	public Sound() {
	}
	public void setChampion(String champion) {
		this.champion = champion;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getChampion() {
		return champion;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	public String getAudio() {
		return audio;
	}
}
