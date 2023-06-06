
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"name",
		"chromaPath",
		"colors",
		"descriptions",
		"rarities"
})
public class Chroma {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("chromaPath")
	private String chromaPath;
	@JsonProperty("colors")
	private List<String> colors;
	@JsonProperty("descriptions")
	private List<Description> descriptions;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("chromaPath")
	public String getChromaPath() {
		return chromaPath;
	}

	@JsonProperty("chromaPath")
	public void setChromaPath(String chromaPath) {
		this.chromaPath = chromaPath;
	}

	@JsonProperty("colors")
	public List<String> getColors() {
		return colors;
	}

	@JsonProperty("colors")
	public void setColors(List<String> colors) {
		this.colors = colors;
	}

	@JsonProperty("descriptions")
	public List<Description> getDescriptions() {
		return descriptions;
	}

	@JsonProperty("descriptions")
	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
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
