
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
		"spellKey",
		"name",
		"abilityIconPath",
		"abilityVideoPath",
		"abilityVideoImagePath",
		"cost",
		"cooldown",
		"description",
		"dynamicDescription",
		"range",
		"costCoefficients",
		"cooldownCoefficients",
		"coefficients",
		"effectAmounts",
		"maxLevel"
})
@JsonDeserialize(builder = CdragonSpell.CdragonSpellBuilder.class)
@Builder
public class CdragonSpell {

	@JsonProperty("spellKey")
	private String spellKey;
	@JsonProperty("name")
	private String name;
	@JsonProperty("abilityIconPath")
	private String abilityIconPath;
	@JsonProperty("abilityVideoPath")
	private String abilityVideoPath;
	@JsonProperty("abilityVideoImagePath")
	private String abilityVideoImagePath;
	@JsonProperty("cost")
	private String cost;
	@JsonProperty("cooldown")
	private String cooldown;
	@JsonProperty("description")
	private String description;
	@JsonProperty("dynamicDescription")
	private String dynamicDescription;
	@JsonProperty("range")
	private List<Double> range;
	@JsonProperty("costCoefficients")
	private List<Double> costCoefficients;
	@JsonProperty("cooldownCoefficients")
	private List<Double> cooldownCoefficients;
	@JsonProperty("effectAmounts")
	private EffectAmounts effectAmounts;
	@JsonProperty("maxLevel")
	private Integer maxLevel;
	@JsonIgnore
	@Builder.Default
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("spellKey")
	public String getSpellKey() {
		return spellKey;
	}

	@JsonProperty("spellKey")
	public void setSpellKey(String spellKey) {
		this.spellKey = spellKey;
	}

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

	@JsonProperty("cost")
	public String getCost() {
		return cost;
	}

	@JsonProperty("cost")
	public void setCost(String cost) {
		this.cost = cost;
	}

	@JsonProperty("cooldown")
	public String getCooldown() {
		return cooldown;
	}

	@JsonProperty("cooldown")
	public void setCooldown(String cooldown) {
		this.cooldown = cooldown;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("dynamicDescription")
	public String getDynamicDescription() {
		return dynamicDescription;
	}

	@JsonProperty("dynamicDescription")
	public void setDynamicDescription(String dynamicDescription) {
		this.dynamicDescription = dynamicDescription;
	}

	@JsonProperty("range")
	public List<Double> getRange() {
		return range;
	}

	@JsonProperty("range")
	public void setRange(List<Double> range) {
		this.range = range;
	}

	@JsonProperty("costCoefficients")
	public List<Double> getCostCoefficients() {
		return costCoefficients;
	}

	@JsonProperty("costCoefficients")
	public void setCostCoefficients(List<Double> costCoefficients) {
		this.costCoefficients = costCoefficients;
	}

	@JsonProperty("cooldownCoefficients")
	public List<Double> getCooldownCoefficients() {
		return cooldownCoefficients;
	}

	@JsonProperty("cooldownCoefficients")
	public void setCooldownCoefficients(List<Double> cooldownCoefficients) {
		this.cooldownCoefficients = cooldownCoefficients;
	}

	@JsonProperty("effectAmounts")
	public EffectAmounts getEffectAmounts() {
		return effectAmounts;
	}

	@JsonProperty("effectAmounts")
	public void setEffectAmounts(EffectAmounts effectAmounts) {
		this.effectAmounts = effectAmounts;
	}

	@JsonProperty("maxLevel")
	public Integer getMaxLevel() {
		return maxLevel;
	}

	@JsonProperty("maxLevel")
	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
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
