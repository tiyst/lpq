package st.tiy.lpq.service.retrieval;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import st.tiy.lpq.repository.ChampionRepository;
import st.tiy.lpq.service.remote.RemoteDataService;

import java.util.List;

@EnableScheduling
public class QuizDataRetrievalService {

	private final List<RemoteDataService> remoteDataServices;

	private final ChampionRepository championRepository;

	public QuizDataRetrievalService(List<RemoteDataService> remoteDataServices, ChampionRepository championRepository) {
		this.remoteDataServices = remoteDataServices;
		this.championRepository = championRepository;
	}

	@Scheduled(cron = "${quiz.data.update.interval}")
	public void updateData() {
		updateQuizData();
	}

	public void updateQuizData() {
		// TODO
	}

	private void mergeChampionData() {
		// TODO
	}

}
