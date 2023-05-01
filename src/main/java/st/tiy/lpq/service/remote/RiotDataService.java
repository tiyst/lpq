package st.tiy.lpq.service.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.RiotSkin;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionResponse;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@ConditionalOnMissingBean(CdragonDataService.class)
public class RiotDataService extends RemoteDataService {

	private final Logger logger = LoggerFactory.getLogger(RiotDataService.class);

	private final String dragonVersionsUrl;
	private final String championsUrl;
	private final String championUrl;
	private final String splashUrl;


	public RiotDataService(RestTemplate restTemplate,
	                       @Value("${riot.ddragon.versions}") String dragonVersionsUrl,
	                       @Value("${riot.ddragon.champions}") String championsUrl,
	                       @Value("${riot.ddragon.champion}") String championUrl,
	                       @Value("${riot.ddragon.splash}") String splashUrl) {
		super(restTemplate);
		this.dragonVersionsUrl = dragonVersionsUrl;
		this.championsUrl = championsUrl;
		this.championUrl = championUrl;
		this.splashUrl = splashUrl;
	}

	public Optional<String> getMostRecentVersion() {
		Optional<String[]> response = getForClass(dragonVersionsUrl, String[].class);

		return response.map(versions -> versions[0]);
	}

	public List<RiotChampion> getChampions(String ddragonVersion) {
		String targetUrl = championsUrl.formatted(ddragonVersion);
		List<RiotChampion> champions = new ArrayList<>();
		Optional<GetChampionsResponse> response = getForClass(targetUrl, GetChampionsResponse.class);

		response.ifPresent(getChampionsResponse -> champions.addAll(getChampionsResponse.getData().values()));

		return champions;
	}

	public List<String> getChampionNames(String ddragonVersion) {
		return getChampions(ddragonVersion).stream()
		                                   .map(RiotChampion::getName)
		                                   .toList();
	}

	public Optional<RiotChampion> getChampion(String ddragonVersion, String championName) {
		String targetUrl = championUrl.formatted(ddragonVersion, championName);
		Optional<GetChampionResponse> response = getForClass(targetUrl, GetChampionResponse.class);

		return response.map(getChampionResponse -> getChampionResponse.getData().get(championName));
	}

	public List<RiotSkin> getSkinsForChampion(String ddragonVersion, String championName) {
		Optional<RiotChampion> champion = getChampion(ddragonVersion, championName);
		if (champion.isEmpty()) {
			return emptyList();
		}

		return champion.get().getRiotSkins();
	}

	public Optional<byte[]> getSplash(String championName, String skinNumber) {
		String targetUrl = splashUrl.formatted(championName, skinNumber);

		return getForClass(targetUrl, byte[].class);
	}
}
