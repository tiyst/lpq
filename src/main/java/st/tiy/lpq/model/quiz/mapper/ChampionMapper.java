package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.riot.champion.Passive;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {SpellMapper.class, SkinMapper.class}, componentModel = "spring")
public abstract class ChampionMapper {

	@Autowired
	protected SpellMapper spellMapper = null;

	@Mapping(source = ".", target = "skins")
	@Mapping(source = ".", target = "spells")
	public abstract Champion mapChampion(RiotChampion riotChampion);

	public List<Spell> spellsMapper(RiotChampion champion) {
		List<Spell> spells = new ArrayList<>();

		Passive passive = champion.getPassive();
		spells.add(passiveToSpell(passive));

		return spells;
	}

	private Spell passiveToSpell(Passive passive) {
		Spell result = new Spell();
		result.setName(passive.getName());
		result.setDescription(passive.getDescription());

		return result;
	}
}
