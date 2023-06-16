package st.tiy.lpq.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import st.tiy.lpq.annotations.Downloadable;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonSkin;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonSpell;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.*;

@Component
public class CdragonAssetDownloader {

	private static final String PATH_PLACEHOLDER = "lol-game-data/assets/";
	public static final String REPLACING_PATH = "plugins/rcp-be-lol-game-data/global/default/";
	private final FileSystem fileSystem;
	private final String baseUrl;
	private final RestTemplate restTemplate;
	private final Logger logger = LoggerFactory.getLogger(CdragonAssetDownloader.class);

	private final List<Field> championFields;
	private final List<Field> skinFields;
	private final List<Field> spellFields;

	private static final String SPECIAL_CHARACTERS = "[-+^:,]";

	public CdragonAssetDownloader(FileSystem fileSystem, @Value("${riot.cdragon.baseUrl}")String baseUrl,
	                              RestTemplate restTemplate) {
		this.fileSystem = fileSystem;
		this.baseUrl = baseUrl;
		this.restTemplate = restTemplate;
		this.championFields = getDownloadableFields(CdragonChampion.class);
		this.skinFields = getDownloadableFields(CdragonSkin.class);
		this.spellFields = getDownloadableFields(CdragonSpell.class);
	}

	private List<Field> getDownloadableFields(Class<?> clazz) {
		return Arrays.stream(clazz.getFields())
		             .filter(field -> field.isAnnotationPresent(Downloadable.class)).toList();
	}

	public void saveChampionMedia(CdragonChampion cdragonChampion){
		if(cdragonChampion.getName().equals("None"))return;
		saveChampionFields(cdragonChampion);
		cdragonChampion.getSkins().forEach(cdragonSkin -> saveSkinFields(cdragonChampion,cdragonSkin));
		cdragonChampion.getCdragonSpells().forEach(cdragonSpell -> saveSpellFields(cdragonChampion,cdragonSpell));
	}

	private void saveChampionFields(CdragonChampion cdragonChampion) {
		for (Field field : championFields) {
			String remotePath = getFieldValue(field, cdragonChampion);
			String localPath = getLocalPath(field,cdragonChampion);
			Optional<byte[]> maybeBytes = getAsset(remotePath);
			maybeBytes.ifPresent(bytes -> fileSystem.saveFile(bytes, localPath));

		}
	}

	private void saveSkinFields(CdragonChampion cdragonChampion,CdragonSkin cdragonSkin) {
		for (Field field : skinFields) {
			String remotePath = getFieldValue(field, cdragonSkin);
			String localPath = getLocalPath(field,cdragonChampion,cdragonSkin);
			Optional<byte[]> maybeBytes = getAsset(remotePath);
			maybeBytes.ifPresent(bytes -> fileSystem.saveFile(bytes, localPath));

		}
	}

	private void saveSpellFields(CdragonChampion cdragonChampion,CdragonSpell cdragonSpell) {
		for (Field field : spellFields) {
			String remotePath = getFieldValue(field, cdragonSpell);
			String localPath = getLocalPath(field,cdragonChampion,cdragonSpell);
			Optional<byte[]> maybeBytes = getAsset(remotePath);
			maybeBytes.ifPresent(bytes -> fileSystem.saveFile(bytes, localPath));

		}
	}


	private String getFieldValue(Field field, Object object) {
		try {
			return field.get(object).toString();
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	private String getLocalPath(Field field,CdragonChampion cdragonChampion) {
		return removeSpecialCharacters(field.getAnnotation(Downloadable.class).filePath()
		     .replace("{championName}",cdragonChampion.getName()));
	}

	private String getLocalPath(Field field,CdragonChampion cdragonChampion,CdragonSkin cdragonSkin) {
		return removeSpecialCharacters(field.getAnnotation(Downloadable.class).filePath()
		            .replace("{championName}",cdragonChampion.getName())
		            .replace("{skinName}",cdragonSkin.getName()));
	}
	private String getLocalPath(Field field,CdragonChampion cdragonChampion,CdragonSpell cdragonSpell) {
		return removeSpecialCharacters(field.getAnnotation(Downloadable.class).filePath()
		            .replace("{championName}",cdragonChampion.getName())
		            .replace("{spellName}",cdragonSpell.getName()));
	}

	private String removeSpecialCharacters(String input){
		return input.replaceAll(SPECIAL_CHARACTERS,"").trim();
	}

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


}
