package st.tiy.lpq.service.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.exception.remote.RetrievalException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public abstract class RemoteDataService {

	private final Logger logger = LoggerFactory.getLogger(RemoteDataService.class);

	private final RestTemplate restTemplate;

	protected RemoteDataService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	protected <T> Optional<T> getForClass(String url, Class<T> clazz) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RetrievalException(String.format("Retrieval for url %s failed", url));
		}
		ResponseEntity<T> response = restTemplate.getForEntity(uri, clazz);

		if (!response.getStatusCode().is2xxSuccessful()) {
			logger.info("getForClass unsuccessful url:{}, returned code {}",url, response.getStatusCode().value());
			return Optional.empty();
		}

		T body = response.getBody();
		if (body == null) {
			logger.info("Response body is empty for url:{}", url);
			return Optional.empty();
		}

		return Optional.of(body);
	}
}
