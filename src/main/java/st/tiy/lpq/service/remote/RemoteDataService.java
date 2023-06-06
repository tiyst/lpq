package st.tiy.lpq.service.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.model.quiz.Champion;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public abstract class RemoteDataService {

	private final Logger logger = LoggerFactory.getLogger(RemoteDataService.class);

	private final RestTemplate restTemplate;

	protected RemoteDataService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public abstract boolean shouldUpdate();

	public abstract List<Champion> getChampions();

	public abstract Optional<byte[]> getAsset(String path);

	protected <T> Optional<T> getForClass(String url, Class<T> clazz) {
		URI uri = URI.create(url);
 		ResponseEntity<T> response = restTemplate.getForEntity(uri, clazz);

		if (!response.getStatusCode().is2xxSuccessful()) {
			logger.error("retrieval for url:{}, returned code {}",url, response.getStatusCode().value());
			return Optional.empty();
		}

		T body = response.getBody();
		if (body == null) {
			logger.error("Response body is empty for url:{}", url);
			return Optional.empty();
		}

		return Optional.of(body);
	}
}
