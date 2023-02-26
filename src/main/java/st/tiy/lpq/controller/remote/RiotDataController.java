package st.tiy.lpq.controller.remote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.service.remote.RiotDataService;

import java.util.List;

@RestController
@RequestMapping(path = "/riot")
public class RiotDataController {

	private RiotDataService riotDataService;

	public RiotDataController(RiotDataService riotDataService) {
		this.riotDataService = riotDataService;
	}

	@GetMapping(path = "version")
	public String getVersion() {
		return riotDataService.getMostRecentVersion().orElse(null);
	}

	@GetMapping(path = "champions/{ddragonVersion}")
	public List<RiotChampion> getChampions(@PathVariable String ddragonVersion) {
		return riotDataService.getChampions(ddragonVersion);
	}

	@GetMapping(path = "champions/{ddragonVersion}/{champion}")
	public RiotChampion getChampion(@PathVariable String ddragonVersion, @PathVariable String champion) {
		return riotDataService.getChampion(ddragonVersion, champion).orElse(null);
	}

	@GetMapping(path = "champions/names/{ddragonVersion}")
	public List<String> getChampionsNames(@PathVariable String ddragonVersion) {
		return riotDataService.getChampionNames(ddragonVersion);
	}

}
