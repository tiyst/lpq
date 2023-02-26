package st.tiy.lpq.model.remote.riot.champion;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"num",
		"name",
		"chromas"
})
public class Skin {

	@JsonProperty("id")
	private String id;
	@JsonProperty("num")
	private Integer num;
	@JsonProperty("name")
	private String name;
	@JsonProperty("chromas")
	private Boolean chromas;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("num")
	public Integer getNum() {
		return num;
	}

	@JsonProperty("num")
	public void setNum(Integer num) {
		this.num = num;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("chromas")
	public Boolean getChromas() {
		return chromas;
	}

	@JsonProperty("chromas")
	public void setChromas(Boolean chromas) {
		this.chromas = chromas;
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
