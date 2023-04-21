package st.tiy.lpq.service.remote;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@EnableScheduling
public class RiotDataRetrievalService {

	private final RiotDataService dataService;

	public RiotDataRetrievalService(RiotDataService dataService) {
		this.dataService = dataService;
	}

	@Scheduled(cron = "${quiz.data.update.interval}")
	public void updateData() {
		System.out.println("Scheduled " + new Date());
	}

}
