package st.tiy.lpq.controller.remote;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.ChampionSummary;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonSkin;
import st.tiy.lpq.service.remote.CdragonDataService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cdragon")
@ConditionalOnMissingBean(CdragonDataController.class)
public class CdragonDataController {

	private final CdragonDataService dataService;

	public CdragonDataController(CdragonDataService dataService) {
		this.dataService = dataService;
	}

	@GetMapping(path = "/champions}")
	public List<ChampionSummary> getChampionSummaries() {
		return dataService.getChampionSummaries();
	}

	@GetMapping(path = "/champions/{championId}")
	public CdragonChampion getChampionById(@PathVariable String championId) {
		return dataService.getChampion(championId).orElse(null);
	}

	@GetMapping(path = "/champions/assets/{championId}/{skinOrder}")
	public byte[] getSplashForSkin(@PathVariable String championId, @PathVariable int skinOrder) {
		Optional<CdragonChampion> champion = dataService.getChampion(championId);
		CdragonChampion cdragonChampion = champion.orElseThrow();
		CdragonSkin cdragonSkin = cdragonChampion.getSkins().get(skinOrder);

		return dataService.getAsset(cdragonSkin.getSplashPath()).orElse(new byte[]{});
	}

}
