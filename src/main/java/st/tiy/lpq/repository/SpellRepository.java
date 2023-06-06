package st.tiy.lpq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.Spell;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long> {

	List<Spell> getSpellsByChampion(Champion champion);

	List<Spell> getSpellsByChampionId(Long championId);

}
