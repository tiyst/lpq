package st.tiy.lpq.service.remote;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.ChampionSummary;

import java.util.*;

@Service
@ConditionalOnProperty(prefix = "riot.cdragon", value = "enabled")
public class CdragonDataService extends RemoteDataService {

	private static final String PATH_PLACEHOLDER = "lol-game-data/assets/";

	private final String baseUrl;
	private final String championSummaryUrl;

	public CdragonDataService(RestTemplate restTemplate,
							  @Value("riot.cdragon.baseUrl") String baseUrl,
							  @Value("riot.cdragon.championSummary") String championSummaryUrl, String championSummaryUrl1) {
		super(restTemplate);
		this.baseUrl = baseUrl;
		this.championSummaryUrl = championSummaryUrl1;
	}

	public List<ChampionSummary> getChampionSummaries() {
		String url = baseUrl + championSummaryUrl; // TODO: 29/4/2023 concat in yml file?
		Optional<ChampionSummary[]> result = getForClass(url, ChampionSummary[].class);

		return result.map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<CdragonChampion> getChampion(String championId) {
		String url = concatPath(baseUrl, "/champions/%s.json", championId);

		return getForClass(url, CdragonChampion.class);
	}

	public Optional<byte[]> getAsset(String path) {
		String normalizedPath = concatPath(path);

		return getForClass(normalizedPath, byte[].class);
	}

	private String concatPath(String append) {
		return concatPath(baseUrl, append);
	}

	private String concatPath(String basePath, String append, String... values) {
		String format = basePath + append;
		String formatted = String.format(format, values);
		return normalizePath(formatted);
	}

	private String normalizePath(String path) {
		if (path.contains(PATH_PLACEHOLDER)) {
			path = path.replace(PATH_PLACEHOLDER, "plugins/rcp-be-lol-game-data/global/default/");
		}

		return path.toLowerCase();
	}

}
