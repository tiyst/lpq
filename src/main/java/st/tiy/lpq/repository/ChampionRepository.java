package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.Champion;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

	Champion getByName(String name);

	Champion getByRiotId(Integer riotId);

}
