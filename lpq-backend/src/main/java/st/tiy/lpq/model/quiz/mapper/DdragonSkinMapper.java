package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Skin;
import st.tiy.lpq.model.remote.riot.champion.RiotSkin;

@Mapper(componentModel = "spring")
public interface DdragonSkinMapper {

	@Mapping(source = "num", target = "number")
	@Mapping(source = "chromas", target = "hasChromas")
	@Mapping(target = "champion", ignore = true)
	Skin toSkin(RiotSkin riotSkin);

}
