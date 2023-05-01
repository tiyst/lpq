package st.tiy.lpq.model.remote.riot.champion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
		"id",
		"key",
		"name",
		"title",
		"blurb",
		"info",
		"tags",
		"partype",
})
public class RiotChampion {

	@JsonProperty("id")
	private String id;
	@JsonProperty("key")
	private String key;
	@JsonProperty("name")
	private String name;
	@JsonProperty("title")
	private String title;
	@JsonProperty("skins")
	private List<RiotSkin> riotSkins;
	@JsonProperty("lore")
	private String lore;
	@JsonProperty("blurb")
	private String blurb;
	@JsonProperty("tags")
	private List<String> tags;
	@JsonProperty("partype")
	private String partype;
	@JsonProperty("spells")
	private List<DdragonSpell> ddragonSpells;
	@JsonProperty("passive")
	private Passive passive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DdragonSpell> getRiotSpells() {
		return ddragonSpells;
	}

	public void setRiotSpells(List<DdragonSpell> ddragonSpells) {
		this.ddragonSpells = ddragonSpells;
	}

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("blurb")
	public String getBlurb() {
		return blurb;
	}

	@JsonProperty("blurb")
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@JsonProperty("partype")
	public String getPartype() {
		return partype;
	}

	@JsonProperty("partype")
	public void setPartype(String partype) {
		this.partype = partype;
	}

	@JsonProperty("skins")
	public List<RiotSkin> getRiotSkins() {
		return riotSkins;
	}

	@JsonProperty("skins")
	public void setRiotSkins(List<RiotSkin> riotSkins) {
		this.riotSkins = riotSkins;
	}

	@JsonProperty("lore")
	public String getLore() {
		return lore;
	}

	@JsonProperty("lore")
	public void setLore(String lore) {
		this.lore = lore;
	}

	@JsonProperty("spells")
	public List<DdragonSpell> getSpells() {
		return ddragonSpells;
	}

	@JsonProperty("spells")
	public void setSpells(List<DdragonSpell> ddragonSpells) {
		this.ddragonSpells = ddragonSpells;
	}

	@JsonProperty("passive")
	public Passive getPassive() {
		return passive;
	}

	@JsonProperty("passive")
	public void setPassive(Passive passive) {
		this.passive = passive;
	}
}
