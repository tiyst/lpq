package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import st.tiy.lpq.model.quiz.Passive;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.riot.champion.DdragonPassive;
import st.tiy.lpq.model.remote.riot.champion.DdragonSpell;

@Mapper(componentModel = "spring")
public interface DdragonSpellMapper {

	@Mapping(target = "champion", ignore = true)
	@Mapping(source = "id", target = "spellKey", qualifiedByName = "spellKeyMapper")
	Spell mapToSpell(DdragonSpell ddragonSpell);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "champion", ignore = true)
	Passive mapPassive(DdragonPassive ddragonPassive);

	@Named("spellKeyMapper")
	default String toSpellKey(int id) {

	}

}
