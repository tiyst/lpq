package st.tiy.lpq.model.quiz.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Skin;
import st.tiy.lpq.model.remote.cdragon.champion.CdragonSkin;
import st.tiy.lpq.model.remote.cdragon.champion.Chroma;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CdragonSkinMapper {

	@Mapping(source = "chromas", target = "hasChromas")
	@Mapping(target = "champion", ignore = true)
	Skin toSkin(CdragonSkin skin);

	default boolean hasChromas(List<Chroma> chromas) {
		return CollectionUtils.isNotEmpty(chromas);
	}

}
