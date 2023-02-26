package st.tiy.lpq.service.remote;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.Skin;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionResponse;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionsResponse;

import java.util.*;

@Service
public class RiotDataService {

	@Value("${riot.ddragon.versions}")
	private String dragonVersionsUrl;
	@Value("${riot.ddragon.champions}")
	private String championsUrl;
	@Value("${riot.ddragon.champion}")
	private String championUrl;
	@Value("${riot.ddragon.splash}")
	private String splashUrl;

	@Autowired
	private RestTemplate restTemplate;

	public Optional<String> getMostRecentVersion() {
		HttpEntity<String> entity = new HttpEntity<>("");
		ResponseEntity<String[]> response = restTemplate.exchange(dragonVersionsUrl, HttpMethod.GET, entity,
		                                                          String[].class, (Object) null);

		String[] body = response.getBody();
		if (body != null && CollectionUtils.isNotEmpty(Arrays.asList(body))) {
				return Optional.ofNullable(body[0]);
		}

		return Optional.empty();
	}

	public List<RiotChampion> getChampions(String ddragonVersion) {
		String targetUrl = championsUrl.formatted(ddragonVersion);
		GetChampionsResponse response = restTemplate.getForObject(targetUrl, GetChampionsResponse.class);

		if (response == null || response.getData() == null) {
			return Collections.emptyList();
		}

		Map<String, RiotChampion> riotChampions = response.getData();
		return riotChampions.values().stream().toList();
	}

	public List<String> getChampionNames(String ddragonVersion) {
		return getChampions(ddragonVersion).stream()
		                                   .map(RiotChampion::getName)
		                                   .toList();
	}

	public Optional<RiotChampion> getChampion(String ddragonVersion, String championName) {
		String targetUrl = championUrl.formatted(ddragonVersion, championName);
		GetChampionResponse response = restTemplate.getForObject(targetUrl, GetChampionResponse.class);

		if (response == null || response.getData() == null) {
			return Optional.empty();
		}

		return Optional.ofNullable(response.getData().get(championName));
	}

	public List<Skin> getSkinsForChampion(String ddragonVersion, String championName) {
		Optional<RiotChampion> champion = getChampion(ddragonVersion, championName);
		if (champion.isPresent()) {
			return champion.get().getSkins();
		}

		return Collections.emptyList();
	}
}
