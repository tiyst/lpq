
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import st.tiy.lpq.annotations.Downloadable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
		"id",
		"name",
		"alias",
		"title",
		"shortBio",
		"tacticalInfo",
		"playstyleInfo",
		"squarePortraitPath",
		"stingerSfxPath",
		"chooseVoPath",
		"banVoPath",
		"roles",
		"skins",
		"passive",
		"spells"
})
@JsonDeserialize(builder = CdragonChampion.CdragonChampionBuilder.class)
@Builder
public class CdragonChampion {

	@JsonProperty("id")
	public Integer id;
	@JsonProperty("name")
	public String name;
	@JsonProperty("alias")
	public String alias;
	@JsonProperty("title")
	public String title;

	@JsonProperty("shortBio")
	public String shortBio;
	@JsonProperty("tacticalInfo")
	public CdragonTacticalInfo tacticalInfo;
	@JsonProperty("playstyleInfo")
	public CdragonPlaystyleInfo playstyleInfo;
	@Downloadable(filePath = "/{championName}/squarePortrait.png")
	@JsonProperty("squarePortraitPath")
	public String squarePortraitPath;
	@Downloadable(filePath = "/{championName}/stingerSfx.ogg")
	@JsonProperty("stingerSfxPath")
	public String stingerSfxPath;
	@Downloadable(filePath = "/{championName}/chooseVo.ogg")
	@JsonProperty("chooseVoPath")
	public String chooseVoPath;
	@Downloadable(filePath = "/{championName}/banVo.ogg")
	@JsonProperty("banVoPath")
	public String banVoPath;
	@JsonProperty("roles")
	public List<String> roles;
	@JsonProperty("skins")
	public List<CdragonSkin> cdragonSkins;
	@JsonProperty("passive")
	public CdragonPassive cdragonPassive;
	@JsonProperty("spells")
	public List<CdragonSpell> cdragonSpells;
	@JsonIgnore
	@Builder.Default
	public Map<String, Object> additionalProperties = new LinkedHashMap<>();

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

	@JsonProperty("alias")
	public String getAlias() {
		return alias;
	}

	@JsonProperty("alias")
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortBio() {
		return shortBio;
	}

	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}

	@JsonProperty("tacticalInfo")
	public CdragonTacticalInfo getTacticalInfo() {
		return tacticalInfo;
	}

	@JsonProperty("tacticalInfo")
	public void setTacticalInfo(CdragonTacticalInfo tacticalInfo) {
		this.tacticalInfo = tacticalInfo;
	}

	@JsonProperty("playstyleInfo")
	public CdragonPlaystyleInfo getPlaystyleInfo() {
		return playstyleInfo;
	}

	@JsonProperty("playstyleInfo")
	public void setPlaystyleInfo(CdragonPlaystyleInfo playstyleInfo) {
		this.playstyleInfo = playstyleInfo;
	}

	@JsonProperty("squarePortraitPath")
	public String getSquarePortraitPath() {
		return squarePortraitPath;
	}

	@JsonProperty("squarePortraitPath")
	public void setSquarePortraitPath(String squarePortraitPath) {
		this.squarePortraitPath = squarePortraitPath;
	}

	@JsonProperty("stingerSfxPath")
	public String getStingerSfxPath() {
		return stingerSfxPath;
	}

	@JsonProperty("stingerSfxPath")
	public void setStingerSfxPath(String stingerSfxPath) {
		this.stingerSfxPath = stingerSfxPath;
	}

	@JsonProperty("chooseVoPath")
	public String getChooseVoPath() {
		return chooseVoPath;
	}

	@JsonProperty("chooseVoPath")
	public void setChooseVoPath(String chooseVoPath) {
		this.chooseVoPath = chooseVoPath;
	}

	@JsonProperty("banVoPath")
	public String getBanVoPath() {
		return banVoPath;
	}

	@JsonProperty("banVoPath")
	public void setBanVoPath(String banVoPath) {
		this.banVoPath = banVoPath;
	}

	@JsonProperty("roles")
	public List<String> getRoles() {
		return roles;
	}

	@JsonProperty("roles")
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@JsonProperty("skins")
	public List<CdragonSkin> getSkins() {
		return cdragonSkins;
	}

	@JsonProperty("skins")
	public void setSkins(List<CdragonSkin> cdragonSkins) {
		this.cdragonSkins = cdragonSkins;
	}

	@JsonProperty("passive")
	public CdragonPassive getCdragonPassive() {
		return cdragonPassive;
	}

	@JsonProperty("passive")
	public void setCdragonPassive(CdragonPassive cdragonPassive) {
		this.cdragonPassive = cdragonPassive;
	}

	@JsonProperty("spells")
	public List<CdragonSpell> getCdragonSpells() {
		return cdragonSpells;
	}

	@JsonProperty("spells")
	public void setCdragonSpells(List<CdragonSpell> cdragonSpells) {
		this.cdragonSpells = cdragonSpells;
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
