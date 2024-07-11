package st.tiy.lpq.service.game;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class EmptyGameDestroyerService {



	@Scheduled(cron = "${quiz.room.cleanup.interval}")
	public void removeEmptyGames() {


	}

}
