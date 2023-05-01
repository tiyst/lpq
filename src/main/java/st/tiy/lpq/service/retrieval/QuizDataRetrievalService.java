package st.tiy.lpq.service.retrieval;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import st.tiy.lpq.service.remote.RiotDataService;

import java.util.Date;

@EnableScheduling
public class QuizDataRetrievalService {

	private final RiotDataService dataService;

	public QuizDataRetrievalService(RiotDataService dataService) {
		this.dataService = dataService;
	}

	@Scheduled(cron = "${quiz.data.update.interval}")
	public void updateData() {
		System.out.println("Scheduled " + new Date());
	}

}
