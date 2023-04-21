package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.riot.champion.RiotSpell;

@Mapper(componentModel = "spring")
public interface SpellMapper {

	@Mapping(source = "id", target = "riotId")
	Spell mapToSpell(RiotSpell riotSpell);

}
