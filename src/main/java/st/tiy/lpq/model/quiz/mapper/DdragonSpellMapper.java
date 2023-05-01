package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.riot.champion.DdragonSpell;

@Mapper(componentModel = "spring")
public interface DdragonSpellMapper {

	@Mapping(target = "champion", ignore = true)
	Spell mapToSpell(DdragonSpell ddragonSpell);

}
