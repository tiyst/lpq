package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.PlaystyleInfo;
import st.tiy.lpq.model.quiz.TacticalInfo;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonChampion;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonPlaystyleInfo;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonTacticalInfo;

@Mapper(uses = {CdragonSpellMapper.class, CdragonSkinMapper.class}, componentModel = "spring")
public interface CdragonChampionMapper {

	@Mapping(source = "id", target = "riotId")
	@Mapping(source = "shortBio", target = "lore")
	@Mapping(source = "cdragonPassive", target = "passive")
	@Mapping(source = ".", target = "spells")
	Champion toChampion(CdragonChampion cdragonChampion);

	@Mapping(target = "id", ignore = true)
	PlaystyleInfo toPlaystyleInfo(CdragonPlaystyleInfo cdragonPlaystyleInfo);

	@Mapping(target = "id", ignore = true)
	TacticalInfo toTacticalInfo(CdragonTacticalInfo cdragonTacticalInfo);

}
