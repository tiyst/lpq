package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.Spell;
import st.tiy.lpq.model.remote.riot.champion.Passive;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;

@Mapper(uses = {DdragonSpellMapper.class, DdragonSkinMapper.class}, componentModel = "spring")
public interface DdragonChampionMapper {

	// TODO: 30/4/2023 map passive
	@Mapping(source = "id", target = "alias")
	@Mapping(source = "riotSkins", target = "skins")
	@Mapping(source = "key", target = "riotId")
	@Mapping(source = "tags", target = "roles")
	@Mapping(source = "partype", target = "resource")
	Champion mapChampion(RiotChampion riotChampion);

	default Spell passiveToSpell(Passive passive) {
		Spell result = new Spell();
		result.setName(passive.getName());
		result.setDescription(passive.getDescription());

		return result;
	}
}
