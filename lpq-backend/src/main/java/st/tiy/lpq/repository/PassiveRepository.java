package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.Passive;

@Repository
public interface PassiveRepository extends JpaRepository<Passive, Long> {

	Passive getPassiveByChampion(Champion champion);

	Passive getPassiveByChampionId(Long id);

}
