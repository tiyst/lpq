package st.tiy.lpq.service.remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.DdragonVersion;
import st.tiy.lpq.model.quiz.mapper.DdragonChampionMapper;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.RiotSkin;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionResponse;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionsResponse;
import st.tiy.lpq.repository.remote.DdragonVersionRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class DdragonDataService extends RemoteDataService {

	private final DdragonVersionRepository versionRepository;

	private final DdragonChampionMapper ddragonChampionMapper;

	private final String dragonVersionsUrl;
	private final String championsUrl;
	private final String championUrl;
	private final String splashUrl;

	public DdragonDataService(RestTemplate restTemplate,
	                          DdragonVersionRepository versionRepository,
	                          DdragonChampionMapper championMapper,
	                          @Value("${riot.ddragon.versions}") String dragonVersionsUrl,
	                          @Value("${riot.ddragon.champions}") String championsUrl,
	                          @Value("${riot.ddragon.champion}") String championUrl,
	                          @Value("${riot.ddragon.splash}") String splashUrl) {
		super(restTemplate);
		this.versionRepository = versionRepository;
		this.ddragonChampionMapper = championMapper;
		this.dragonVersionsUrl = dragonVersionsUrl;
		this.championsUrl = championsUrl;
		this.championUrl = championUrl;
		this.splashUrl = splashUrl;
	}

	@Override
	public boolean shouldUpdate() {
		DdragonVersion version = versionRepository.findTopByOrderByIdDesc();
		String ddragonVersion = version.getVersion();
		Optional<String> mostRecentVersion = getMostRecentVersion();

		return mostRecentVersion.map(s -> s.equals(ddragonVersion)).orElse(false);
	}

	@Override
	public List<Champion> getChampions() {
		DdragonVersion version = versionRepository.findTopByOrderByIdDesc();
		List<RiotChampion> ddragonChampions = getDdragonChampions(version.getVersion());

		return ddragonChampions.stream()
		                       .map(ddragonChampionMapper::mapChampion)
		                       .toList();
	}

	@Override
	public Optional<byte[]> getAsset(String path) {
		return getForClass(path, byte[].class);
	}

	public Optional<String> getMostRecentVersion() {
		Optional<String[]> response = getForClass(dragonVersionsUrl, String[].class);

		return response.map(versions -> versions[0]);
	}

	public List<RiotChampion> getDdragonChampions(String ddragonVersion) {
		String targetUrl = championsUrl.formatted(ddragonVersion);
		Optional<GetChampionsResponse> response = getForClass(targetUrl, GetChampionsResponse.class);

		return response.map(r -> r.getData().values().stream().toList())
		               .orElseGet(List::of);
	}

	public List<String> getChampionNames(String ddragonVersion) {
		return getDdragonChampions(ddragonVersion).stream()
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
