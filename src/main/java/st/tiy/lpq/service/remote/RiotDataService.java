package st.tiy.lpq.service.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
public class RiotDataService {

	private final Logger logger = LoggerFactory.getLogger(RiotDataService.class);

	private final String dragonVersionsUrl;
	private final String championsUrl;
	private final String championUrl;
	private final String splashUrl;

	private final RestTemplate restTemplate;

	public RiotDataService(RestTemplate restTemplate,
	                       @Value("${riot.ddragon.versions}") String dragonVersionsUrl,
	                       @Value("${riot.ddragon.champions}") String championsUrl,
	                       @Value("${riot.ddragon.champion}") String championUrl,
	                       @Value("${riot.ddragon.splash}") String splashUrl) {
		this.restTemplate = restTemplate;
		this.dragonVersionsUrl = dragonVersionsUrl;
		this.championsUrl = championsUrl;
		this.championUrl = championUrl;
		this.splashUrl = splashUrl;
	}

	public Optional<String> getMostRecentVersion() {
		HttpEntity<String> entity = new HttpEntity<>("");
		ResponseEntity<String[]> response = restTemplate.exchange(dragonVersionsUrl, HttpMethod.GET, entity,
		                                                          String[].class, (Object) null);

		if (!response.getStatusCode().is2xxSuccessful()) {
			logger.info("Getting most recent version unsuccessful, returned code {}", response.getStatusCode().value());
			return Optional.empty();
		}

		String[] body = response.getBody();
		if (body == null || isEmpty(Arrays.asList(body))) {
			logger.info("getMostRecentVersion is not present");
			return Optional.empty();
		}

		return Optional.ofNullable(body[0]);
	}

	public List<RiotChampion> getChampions(String ddragonVersion) {
		String targetUrl = championsUrl.formatted(ddragonVersion);
		GetChampionsResponse response = restTemplate.getForObject(targetUrl, GetChampionsResponse.class);

		if (response == null || response.getData() == null) {
			logger.info("getChampions is not present");
			return emptyList();
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
			logger.info("getChampion is not present");
			return Optional.empty();
		}

		return Optional.ofNullable(response.getData().get(championName));
	}

	public List<Skin> getSkinsForChampion(String ddragonVersion, String championName) {
		Optional<RiotChampion> champion = getChampion(ddragonVersion, championName);
		if (champion.isEmpty()) {
			logger.info("getSkinsForChampion is not present");
			return emptyList();
		}

		return champion.get().getSkins();
	}

	public Optional<byte[]> getSplash(String championName, String skinNumber) {
		String targetUrl = splashUrl.formatted(championName, skinNumber);

		byte[] splash = restTemplate.getForObject(targetUrl, byte[].class);
		if (splash == null || splash.length == 0) {
			logger.info("getSkinsForChampion is not present");
			return Optional.empty();
		}

		return Optional.of(splash);
	}
}
