
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"style",
		"difficulty",
		"damageType"
})
@Builder
public class CdragonTacticalInfo {

	@JsonProperty("style")
	private Integer style;
	@JsonProperty("difficulty")
	private Integer difficulty;
	@JsonProperty("damageType")
	private String damageType;
	@JsonIgnore
	@Builder.Default
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("style")
	public Integer getStyle() {
		return style;
	}

	@JsonProperty("style")
	public void setStyle(Integer style) {
		this.style = style;
	}

	@JsonProperty("difficulty")
	public Integer getDifficulty() {
		return difficulty;
	}

	@JsonProperty("difficulty")
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	@JsonProperty("damageType")
	public String getDamageType() {
		return damageType;
	}

	@JsonProperty("damageType")
	public void setDamageType(String damageType) {
		this.damageType = damageType;
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
