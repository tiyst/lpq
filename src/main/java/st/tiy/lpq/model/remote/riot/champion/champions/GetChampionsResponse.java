package st.tiy.lpq.model.remote.riot.champion.champions;

import com.fasterxml.jackson.annotation.*;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"type",
		"format",
		"version",
		"data"
})
public class GetChampionsResponse {

	@JsonProperty("type")
	private String type;
	@JsonProperty("format")
	private String format;
	@JsonProperty("version")
	private String version;
	@JsonProperty("data")
	private Map<String, RiotChampion> data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("format")
	public String getFormat() {
		return format;
	}

	@JsonProperty("format")
	public void setFormat(String format) {
		this.format = format;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@JsonProperty("data")
	public Map<String, RiotChampion> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Map<String, RiotChampion> data) {
		this.data = data;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
