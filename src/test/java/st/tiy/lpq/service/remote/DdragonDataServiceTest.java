package st.tiy.lpq.service.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.quiz.mapper.DdragonChampionMapper;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionsResponse;
import st.tiy.lpq.repository.remote.DdragonVersionRepository;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@TestPropertySource(properties = {
		"riot.ddragon.versions=1",
		"riot.ddragon.champions=2",
		"riot.ddragon.champion=3%s",
		"riot.ddragon.splash=4%s_%s",
})
class DdragonDataServiceTest {

	private static final String VERSION_URL = "VERSION";
	private static final String CHAMPIONS_URL = "%s_CHAMPS";
	private static final String CHAMPION_URL = "%s_CHAMP_%s";
	private static final String SPLASH_URL = "SPLASH_%s_%s";

	public static RestTemplate restTemplate;

	private static DdragonDataService ddragonDataService;
	private static DdragonVersionRepository versionRepository;
	private static DdragonChampionMapper championMapper;

	@BeforeEach
	void beforeEach() {
		restTemplate = mock(RestTemplate.class);
		versionRepository = mock(DdragonVersionRepository.class);
		championMapper = mock(DdragonChampionMapper.class);
		ddragonDataService = new DdragonDataService(restTemplate, versionRepository, championMapper, VERSION_URL,
		                                            CHAMPIONS_URL, CHAMPION_URL, SPLASH_URL);
	}

	@Test
	void versionRetrievalSuccessful() {
		URI url = URI.create(VERSION_URL);

		when(restTemplate.getForEntity(url, String[].class))
				.thenReturn(createStringArrayResponse("1", "2", "3"));

		Optional<String> mostRecentVersion = ddragonDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).contains("1");
		verify(restTemplate).getForEntity(url, String[].class);
	}

	@Test
	void versionRetrievalReturnEmpty() {
		URI url = URI.create(VERSION_URL);
		when(restTemplate.getForEntity(url, String[].class))
				.thenReturn(createStringArrayResponse("v"));

		Optional<String> mostRecentVersion = ddragonDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).isNotEmpty()
		                             .contains("v");
		verify(restTemplate).getForEntity(url, String[].class);
	}

	@Test
	void versionRetrievalReturnErrorCode() {
		URI url = URI.create(VERSION_URL);
		when(restTemplate.getForEntity(url, String[].class)).thenReturn(createResponseWithCode(404));

		Optional<String> mostRecentVersion = ddragonDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).isEmpty();
		verify(restTemplate).getForEntity(url, String[].class);
	}

	@Test
	void championsRetrieval() {
		int ddragonVersion = 1;
		String targetUrl = CHAMPIONS_URL.formatted(ddragonVersion);
		URI url = URI.create(targetUrl);

		when(restTemplate.getForEntity(url, GetChampionsResponse.class)).thenReturn(createChampionsResponse());
		List<String> mostRecentVersion = ddragonDataService.getChampionNames(String.valueOf(ddragonVersion));

		assertThat(mostRecentVersion).containsExactly("Ahri");
		verify(restTemplate).getForEntity(url, GetChampionsResponse.class);
	}

	private ResponseEntity<GetChampionsResponse> createChampionsResponse(RiotChampion champion) {
		GetChampionsResponse response = new GetChampionsResponse();
		response.setData(Map.of(champion.getName(), champion));

		return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
	}

	private ResponseEntity<GetChampionsResponse> createChampionsResponse() {
		RiotChampion champion = createChampion("Ahri");
		return createChampionsResponse(champion);
	}

	private RiotChampion createChampion(String name) {
		RiotChampion champion = new RiotChampion();
		champion.setName(name);

		return champion;
	}

	private ResponseEntity<String[]> createStringArrayResponse(String... values) {
		return new ResponseEntity<>(values, HttpStatusCode.valueOf(200));
	}

	private ResponseEntity<String[]> createResponseWithCode(int code) {
		return new ResponseEntity<>(null, HttpStatusCode.valueOf(code));
	}
}