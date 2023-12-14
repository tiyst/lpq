package st.tiy.lpq.controller.remote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.RiotSkin;
import st.tiy.lpq.service.remote.DdragonDataService;
import st.tiy.lpq.utils.environments.Dev;

import java.util.List;

@RestController
@RequestMapping(path = "/riot")
@Dev
public class RiotDataController {

	private final DdragonDataService ddragonDataService;

	public RiotDataController(DdragonDataService ddragonDataService) {
		this.ddragonDataService = ddragonDataService;
	}

	@GetMapping(path = "version")
	public String getVersion() {
		return ddragonDataService.getMostRecentVersion().orElse(null);
	}

	@GetMapping(path = "champions/{ddragonVersion}")
	public List<RiotChampion> getChampions(@PathVariable String ddragonVersion) {
		return ddragonDataService.getDdragonChampions(ddragonVersion);
	}

	@GetMapping(path = "champions/{ddragonVersion}/{champion}")
	public RiotChampion getChampion(@PathVariable String ddragonVersion, @PathVariable String champion) {
		return ddragonDataService.getChampion(ddragonVersion, champion).orElse(null);
	}

	@GetMapping(path = "champions/names/{ddragonVersion}")
	public List<String> getChampionsNames(@PathVariable String ddragonVersion) {
		return ddragonDataService.getChampionNames(ddragonVersion);
	}

	@GetMapping(path = "champions/skins/{ddragonVersion}/{champion}")
	public List<RiotSkin> getChampionSkins(@PathVariable String ddragonVersion, @PathVariable String champion) {
		return ddragonDataService.getSkinsForChampion(ddragonVersion, champion);
	}

	@GetMapping(path = "champions/splash/{champion}/{number}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getSplash(@PathVariable String champion, @PathVariable String number) {
		return ddragonDataService.getSplash(champion, number).orElse(null);
	}

}
