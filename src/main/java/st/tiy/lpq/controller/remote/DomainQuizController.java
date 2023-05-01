package st.tiy.lpq.controller.remote;

import st.tiy.lpq.model.quiz.mapper.DdragonChampionMapper;
import st.tiy.lpq.service.remote.RemoteDataService;
import st.tiy.lpq.service.remote.RiotDataService;

//@RestController
public class DomainQuizController {

	private DdragonChampionMapper ddragonChampionMapper;
	private final RemoteDataService riotDataService;


	public DomainQuizController(DdragonChampionMapper ddragonChampionMapper, RiotDataService riotDataService) {
		this.ddragonChampionMapper = ddragonChampionMapper;
		this.riotDataService = riotDataService;
	}

//	@GetMapping("/domain/{championName}")
//	public Champion a(@PathVariable String championName) {
//		Optional<RiotChampion> champion = riotDataService.getChampion("13.8.1", championName);
//		return champion.map(riotChampion -> ddragonChampionMapper.mapChampion(riotChampion)).orElse(null);
//
//	}
}
