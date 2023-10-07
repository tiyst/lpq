package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;

@Mapper(uses = {DdragonSpellMapper.class, DdragonSkinMapper.class}, componentModel = "spring")
public interface DdragonChampionMapper {

	@Mapping(source = "id", target = "alias")
	@Mapping(source = "riotSkins", target = "skins")
	@Mapping(source = "key", target = "riotId")
	@Mapping(source = "tags", target = "roles")
	@Mapping(source = "partype", target = "resource")
	@Mapping(target = "tacticalInfo", ignore = true)
	@Mapping(target = "playstyleInfo", ignore = true)
	Champion mapChampion(RiotChampion riotChampion);

}
