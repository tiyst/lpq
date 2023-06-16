
package st.tiy.lpq.model.remote.cdragon.champion;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import st.tiy.lpq.annotations.Downloadable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"isBase",
		"name",
		"splashPath",
		"uncenteredSplashPath",
		"tilePath",
		"loadScreenPath",
		"skinType",
		"rarity",
		"isLegacy",
		"splashVideoPath",
		"collectionSplashVideoPath",
		"featuresText",
		"chromaPath",
		"emblems",
		"regionRarityId",
		"rarityGemPath",
		"skinLines",
		"description",
		"chromas",
		"loadScreenVintagePath"
})
@JsonDeserialize(builder = CdragonSkin.CdragonSkinBuilder.class)
@Builder
public class CdragonSkin {

	@JsonProperty("id")
	public Integer id;
	@JsonProperty("isBase")
	public Boolean isBase;
	@JsonProperty("name")
	public String name;
	@Downloadable(filePath = "/{championName}/skins/{skinName}/splash.png")
	@JsonProperty("splashPath")
	public String splashPath;
	@Downloadable(filePath = "/{championName}/skins/{skinName}/uncenteredSplash.png")
	@JsonProperty("uncenteredSplashPath")
	public String uncenteredSplashPath;
	@Downloadable(filePath = "/{championName}/skins/{skinName}/tile.png")
	@JsonProperty("tilePath")
	public String tilePath;
	@Downloadable(filePath = "/{championName}/skins/{skinName}/loadScreen.png")
	@JsonProperty("loadScreenPath")
	public String loadScreenPath;
	@JsonProperty("skinType")
	public String skinType;
	@JsonProperty("rarity")
	public String rarity;
	@JsonProperty("isLegacy")
	public Boolean isLegacy;
	@JsonProperty("splashVideoPath")
	public Object splashVideoPath;
	@JsonProperty("collectionSplashVideoPath")
	public Object collectionSplashVideoPath;
	@JsonProperty("featuresText")
	public Object featuresText;
	@JsonProperty("skinLines")
	public List<SkinLine> skinLines;
	@JsonProperty("description")
	public String description;
	@JsonProperty("chromas")
	public List<Chroma> chromas;
	@JsonProperty("loadScreenVintagePath")
	public String loadScreenVintagePath;
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

	@JsonProperty("isBase")
	public Boolean getIsBase() {
		return isBase;
	}

	@JsonProperty("isBase")
	public void setIsBase(Boolean isBase) {
		this.isBase = isBase;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("splashPath")
	public String getSplashPath() {
		return splashPath;
	}

	@JsonProperty("splashPath")
	public void setSplashPath(String splashPath) {
		this.splashPath = splashPath;
	}

	@JsonProperty("uncenteredSplashPath")
	public String getUncenteredSplashPath() {
		return uncenteredSplashPath;
	}

	@JsonProperty("uncenteredSplashPath")
	public void setUncenteredSplashPath(String uncenteredSplashPath) {
		this.uncenteredSplashPath = uncenteredSplashPath;
	}

	@JsonProperty("tilePath")
	public String getTilePath() {
		return tilePath;
	}

	@JsonProperty("tilePath")
	public void setTilePath(String tilePath) {
		this.tilePath = tilePath;
	}

	@JsonProperty("loadScreenPath")
	public String getLoadScreenPath() {
		return loadScreenPath;
	}

	@JsonProperty("loadScreenPath")
	public void setLoadScreenPath(String loadScreenPath) {
		this.loadScreenPath = loadScreenPath;
	}

	@JsonProperty("skinType")
	public String getSkinType() {
		return skinType;
	}

	@JsonProperty("skinType")
	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	@JsonProperty("rarity")
	public String getRarity() {
		return rarity;
	}

	@JsonProperty("rarity")
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	@JsonProperty("isLegacy")
	public Boolean getIsLegacy() {
		return isLegacy;
	}

	@JsonProperty("isLegacy")
	public void setIsLegacy(Boolean isLegacy) {
		this.isLegacy = isLegacy;
	}

	@JsonProperty("splashVideoPath")
	public Object getSplashVideoPath() {
		return splashVideoPath;
	}

	@JsonProperty("splashVideoPath")
	public void setSplashVideoPath(Object splashVideoPath) {
		this.splashVideoPath = splashVideoPath;
	}

	@JsonProperty("collectionSplashVideoPath")
	public Object getCollectionSplashVideoPath() {
		return collectionSplashVideoPath;
	}

	@JsonProperty("collectionSplashVideoPath")
	public void setCollectionSplashVideoPath(Object collectionSplashVideoPath) {
		this.collectionSplashVideoPath = collectionSplashVideoPath;
	}

	@JsonProperty("featuresText")
	public Object getFeaturesText() {
		return featuresText;
	}

	@JsonProperty("featuresText")
	public void setFeaturesText(Object featuresText) {
		this.featuresText = featuresText;
	}

	@JsonProperty("skinLines")
	public List<SkinLine> getSkinLines() {
		return skinLines;
	}

	@JsonProperty("skinLines")
	public void setSkinLines(List<SkinLine> skinLines) {
		this.skinLines = skinLines;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("chromas")
	public List<Chroma> getChromas() {
		return chromas;
	}

	@JsonProperty("chromas")
	public void setChromas(List<Chroma> chromas) {
		this.chromas = chromas;
	}

	@JsonProperty("loadScreenVintagePath")
	public String getLoadScreenVintagePath() {
		return loadScreenVintagePath;
	}

	@JsonProperty("loadScreenVintagePath")
	public void setLoadScreenVintagePath(String loadScreenVintagePath) {
		this.loadScreenVintagePath = loadScreenVintagePath;
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
