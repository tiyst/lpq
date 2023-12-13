package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.Skin;

@Repository
public interface SkinRepository extends JpaRepository<Skin, Long> {

	Skin getSkinByChampion(Champion champion);

	Skin getSkinByChampionId(Long championId);

}
