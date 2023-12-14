package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.TacticalInfo;

@Repository
public interface TacticalInfoRepository extends JpaRepository<TacticalInfo, Long> {

	TacticalInfo getTacticalInfoByChampion(Champion champion);

	TacticalInfo getTacticalInfoByChampionId(Long championId);
}
