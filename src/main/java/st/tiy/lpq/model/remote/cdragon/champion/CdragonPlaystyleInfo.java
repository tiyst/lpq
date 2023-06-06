
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"damage",
		"durability",
		"crowdControl",
		"mobility",
		"utility"
})
@JsonDeserialize(builder = CdragonPlaystyleInfo.CdragonPlaystyleInfoBuilder.class)
@Builder
public class CdragonPlaystyleInfo {

	@JsonProperty("damage")
	private Integer damage;
	@JsonProperty("durability")
	private Integer durability;
	@JsonProperty("crowdControl")
	private Integer crowdControl;
	@JsonProperty("mobility")
	private Integer mobility;
	@JsonProperty("utility")
	private Integer utility;
	@JsonIgnore
	@Builder.Default
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("damage")
	public Integer getDamage() {
		return damage;
	}

	@JsonProperty("damage")
	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	@JsonProperty("durability")
	public Integer getDurability() {
		return durability;
	}

	@JsonProperty("durability")
	public void setDurability(Integer durability) {
		this.durability = durability;
	}

	@JsonProperty("crowdControl")
	public Integer getCrowdControl() {
		return crowdControl;
	}

	@JsonProperty("crowdControl")
	public void setCrowdControl(Integer crowdControl) {
		this.crowdControl = crowdControl;
	}

	@JsonProperty("mobility")
	public Integer getMobility() {
		return mobility;
	}

	@JsonProperty("mobility")
	public void setMobility(Integer mobility) {
		this.mobility = mobility;
	}

	@JsonProperty("utility")
	public Integer getUtility() {
		return utility;
	}

	@JsonProperty("utility")
	public void setUtility(Integer utility) {
		this.utility = utility;
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
