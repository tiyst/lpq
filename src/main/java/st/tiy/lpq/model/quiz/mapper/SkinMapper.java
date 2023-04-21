package st.tiy.lpq.model.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import st.tiy.lpq.model.quiz.Skin;
import st.tiy.lpq.model.remote.riot.champion.RiotChampion;
import st.tiy.lpq.model.remote.riot.champion.RiotSkin;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Mapper(componentModel = "spring")
public interface SkinMapper {

	@Mapping(source = "num", target = "number")
	@Mapping(source = "chromas", target = "hasChromas")
	Skin toSkin(RiotSkin riotSkin);

	default List<Skin> toSkins(RiotChampion champion) {
		List<RiotSkin> riotSkins = champion.getRiotSkins();
		if (isEmpty(riotSkins)) {
			return List.of();
		}

		return riotSkins.stream()
		                .map(this::toSkin)
		                .map(skin -> {
			                skin.setChampionName(champion.getName());
							return skin;
		                })
						.toList();
	}

}
