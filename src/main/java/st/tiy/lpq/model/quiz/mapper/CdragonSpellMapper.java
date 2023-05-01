package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import st.tiy.lpq.model.quiz.Passive;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonPassive;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonSpell;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CdragonSpellMapper {

	public List<Spell> toSpells(CdragonChampion champion) {
		return champion.getCdragonSpells().stream()
		               .map(this::toSpell)
		               .toList();
	}

	public abstract Passive toPassive(CdragonPassive cdragonPassive);

	public abstract Spell toSpell(CdragonSpell cdragonSpell);

}
