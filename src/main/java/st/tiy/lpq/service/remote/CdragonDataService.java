package st.tiy.lpq.service.remote;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.components.FileSystem;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.mapper.CdragonChampionMapper;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.ChampionSummary;
import st.tiy.lpq.repository.remote.CdragonVersionRepository;

import java.util.*;

@Service
public class CdragonDataService extends RemoteDataService {

	private static final String PATH_PLACEHOLDER = "lol-game-data/assets/";
	public static final String REPLACING_PATH = "plugins/rcp-be-lol-game-data/global/default/";

	private final CdragonChampionMapper mapper;

	private final CdragonVersionRepository versionRepository;
	private final FileSystem fileSystem;

	private final String baseUrl;
	private final String championSummaryUrl;
	private final String championUrl;

	public CdragonDataService(RestTemplate restTemplate,
							  CdragonChampionMapper mapper,
							  CdragonVersionRepository versionRepository,
							  @Value("${riot.cdragon.baseUrl}") String baseUrl,
							  @Value("${riot.cdragon.championSummary}") String championSummaryUrl,
							  @Value("${riot.cdragon.champion}") String championUrl,
							  FileSystem fileSystem) {
		super(restTemplate);
		this.versionRepository = versionRepository;
		this.mapper = mapper;
		this.baseUrl = baseUrl;
		this.championSummaryUrl = championSummaryUrl;
		this.championUrl = championUrl;
		this.fileSystem = fileSystem;
	}

	@Override
	public boolean shouldUpdate() {
		// TODO: 6/6/2023 Cdragon model versioning
		return true;
	}

	@Override
	public List<Champion> getChampions() {
		List<ChampionSummary> championSummaries = getChampionSummaries();
		return championSummaries.stream()
		                        .map(summary -> getChampion(summary.getName()))
		                        .filter(Optional::isPresent)
		                        .map(Optional::get)
		                        .map(mapper::toChampion)
		                        .toList();
	}

	public List<ChampionSummary> getChampionSummaries() {
		String url = baseUrl + championSummaryUrl;
		Optional<ChampionSummary[]> result = getForClass(url, ChampionSummary[].class);

		return result.map(Arrays::asList).orElse(Collections.emptyList());
	}

	public Optional<CdragonChampion> getChampion(String championId)  {
		String url = concatPath(baseUrl, championUrl, championId);

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
			path = path.replace(PATH_PLACEHOLDER, REPLACING_PATH);
		}

		return path.toLowerCase();
	}

	private void saveChampionMedia(CdragonChampion cdragonChampion){
		String name = cdragonChampion.getName();
		Map<String, String> remoteToLocalPathMap = Map.of(
				cdragonChampion.getBanVoPath(), "banVo.ogg",
				cdragonChampion.getChooseVoPath(), "chooseVo.ogg",
				cdragonChampion.getSquarePortraitPath(), "squarePortrait.png",
				cdragonChampion.getStingerSfxPath(), "stingerSfx.ogg");

		for (Map.Entry<String, String> entry : remoteToLocalPathMap.entrySet()) {
			Optional<byte[]> maybeBytes = getAsset(entry.getKey());
			maybeBytes.ifPresent(bytes -> fileSystem.saveFile(bytes, name, entry.getValue()));
		}
	}
}
