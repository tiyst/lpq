package st.tiy.lpq.service.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.champions.GetChampionsResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
		"riot.ddragon.versions=1",
		"riot.ddragon.champions=2",
		"riot.ddragon.champion=3%s",
		"riot.ddragon.splash=4%s_%s",
})
class RiotDataServiceTest {

	private static final String VERSION_URL = "VERSION";
	private static final String CHAMPIONS_URL = "%s_CHAMPS";
	private static final String CHAMPION_URL = "%s_CHAMP_%s";
	private static final String SPLASH_URL = "SPLASH_%s_%s";

	@Autowired
	public static RestTemplate restTemplate;

	@Autowired
	private static RiotDataService riotDataService;

	@BeforeEach
	void beforeEach() {
		restTemplate = mock(RestTemplate.class);
		riotDataService = new RiotDataService(restTemplate, VERSION_URL, CHAMPIONS_URL, CHAMPION_URL, SPLASH_URL);
	}

	@Test
	void versionRetrievalSuccessful() {
		when(restTemplate.exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                           eq(String[].class), any(Object.class)))
				.thenReturn(createStringArrayResponse("1", "2", "3"));
		Optional<String> mostRecentVersion = riotDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).contains("1");
		verify(restTemplate).exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                              eq(String[].class), eq((Object) null));
	}

	@Test
	void versionRetrievalReturnEmpty() {
		when(restTemplate.exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                           eq(String[].class), any(Object.class)))
				.thenReturn(createStringArrayResponse());
		Optional<String> mostRecentVersion = riotDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).isEmpty();
		verify(restTemplate).exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                              eq(String[].class), eq((Object) null));
	}

	@Test
	void versionRetrievalReturnErrorCode() {
		when(restTemplate.exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                           eq(String[].class), any(Object.class)))
				.thenReturn(createResponseWithCode(404));
		Optional<String> mostRecentVersion = riotDataService.getMostRecentVersion();

		assertThat(mostRecentVersion).isEmpty();
		verify(restTemplate).exchange(eq(VERSION_URL), eq(HttpMethod.GET), any(HttpEntity.class),
		                              eq(String[].class), eq((Object) null));

	}

	@Test
	void championsRetrieval() {
		int ddragonVersion = 1;
		String targetUrl = CHAMPIONS_URL.formatted(ddragonVersion);

		when(restTemplate.getForObject(targetUrl, GetChampionsResponse.class)).thenReturn(createChampionsResponse());
		List<String> mostRecentVersion = riotDataService.getChampionNames(String.valueOf(ddragonVersion));

		assertThat(mostRecentVersion).containsExactly("Ahri");
		verify(restTemplate).getForObject(targetUrl, GetChampionsResponse.class);
	}

	private GetChampionsResponse createChampionsResponse(RiotChampion champion) {
		GetChampionsResponse response = new GetChampionsResponse();
		response.setData(Map.of(champion.getName(), champion));

		return response;
	}

	private GetChampionsResponse createChampionsResponse() {
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