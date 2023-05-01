
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"name",
		"abilityIconPath",
		"abilityVideoPath",
		"abilityVideoImagePath",
		"description"
})
@Builder
public class CdragonPassive {

	@JsonProperty("name")
	private String name;
	@JsonProperty("abilityIconPath")
	private String abilityIconPath;
	@JsonProperty("abilityVideoPath")
	private String abilityVideoPath;
	@JsonProperty("abilityVideoImagePath")
	private String abilityVideoImagePath;
	@JsonProperty("description")
	private String description;
	@JsonIgnore
	@Builder.Default
 	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("abilityIconPath")
	public String getAbilityIconPath() {
		return abilityIconPath;
	}

	@JsonProperty("abilityIconPath")
	public void setAbilityIconPath(String abilityIconPath) {
		this.abilityIconPath = abilityIconPath;
	}

	@JsonProperty("abilityVideoPath")
	public String getAbilityVideoPath() {
		return abilityVideoPath;
	}

	@JsonProperty("abilityVideoPath")
	public void setAbilityVideoPath(String abilityVideoPath) {
		this.abilityVideoPath = abilityVideoPath;
	}

	@JsonProperty("abilityVideoImagePath")
	public String getAbilityVideoImagePath() {
		return abilityVideoImagePath;
	}

	@JsonProperty("abilityVideoImagePath")
	public void setAbilityVideoImagePath(String abilityVideoImagePath) {
		this.abilityVideoImagePath = abilityVideoImagePath;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
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
