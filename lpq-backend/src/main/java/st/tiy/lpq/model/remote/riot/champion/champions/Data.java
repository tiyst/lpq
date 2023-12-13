package st.tiy.lpq.model.remote.riot.champion.champions;

import com.fasterxml.jackson.annotation.*;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
	@JsonProperty
	private List<RiotChampion> riotChampions;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty
	public List<RiotChampion> getRiotChampions() {
		return riotChampions;
	}

	@JsonProperty
	public void setRiotChampions(List<RiotChampion> riotChampion) {
		this.riotChampions = riotChampion;
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
