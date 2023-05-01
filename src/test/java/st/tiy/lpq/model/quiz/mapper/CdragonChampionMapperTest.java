package st.tiy.lpq.model.quiz.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import st.tiy.lpq.model.quiz.Champion;
import st.tiy.lpq.model.quiz.PlaystyleInfo;
import st.tiy.lpq.model.quiz.TacticalInfo;
import st.tiy.lpq.model.remote.cdragon.champion.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(classes = {CdragonChampionMapperImpl.class, CdragonSkinMapperImpl.class, CdragonSpellMapperImpl.class})
@ContextConfiguration(classes = {CdragonChampionMapperImpl.class, CdragonSkinMapperImpl.class, CdragonSpellMapperImpl.class})
@ExtendWith(SpringExtension.class)
class CdragonChampionMapperTest {

	@Autowired
	private CdragonChampionMapper mapper;

	@Test
	void championMappedCorrectly() {
		CdragonChampion cdragonChampion = createChampion();

		Champion champion = mapper.toChampion(cdragonChampion);

		assertThat(champion.getName()).isEqualTo("championName");
		assertThat(champion.getAlias()).isEqualTo("championAlias");
		assertThat(champion.getTitle()).isEqualTo("championTitle");
		assertThat(champion.getLore()).isEqualTo("shortBio");
		assertThat(champion.getRoles()).containsOnly("role1", "role2");
		assertTacticalInfo(champion.getTacticalInfo());
		assertPlaystyleInfo(champion.getPlaystyleInfo());
	}

	private void assertTacticalInfo(TacticalInfo tacticalInfo) {
		assertThat(tacticalInfo.getDamageType()).isEqualTo("damageType");
		assertThat(tacticalInfo.getDifficulty()).isEqualTo(5);
		assertThat(tacticalInfo.getStyle()).isEqualTo(1);
	}

	private void assertPlaystyleInfo(PlaystyleInfo playstyleInfo) {
		assertThat(playstyleInfo.getCrowdControl()).isEqualTo(1);
		assertThat(playstyleInfo.getUtility()).isEqualTo(2);
		assertThat(playstyleInfo.getDamage()).isEqualTo(3);
		assertThat(playstyleInfo.getMobility()).isEqualTo(4);
		assertThat(playstyleInfo.getDurability()).isEqualTo(5);
	}

	private CdragonChampion createChampion() {
		return CdragonChampion.builder()
		                      .id(1324)
		                      .name("championName")
		                      .alias("championAlias")
		                      .title("championTitle")
		                      .shortBio("shortBio")
		                      .roles(List.of("role1", "role2"))
		                      .tacticalInfo(createTacticalInfo())
		                      .playstyleInfo(createPlaystyleInfo())
		                      .cdragonPassive(createPassive())
		                      .cdragonSkins(createSkins())
		                      .cdragonSpells(createSpells())
		                      .build();
	}

	private List<CdragonSpell> createSpells() {
		return List.of(CdragonSpell.builder()
		                           .spellKey("q")
		                           .name("spellName")
		                           .description("spellDescription")
		                           .cost("69")
		                           .range(List.of(420d))
		                           .cooldown("15")
		                           .build()
		);
	}

	private List<CdragonSkin> createSkins() {
		return List.of(CdragonSkin.builder()
		                          .id(1)
		                          .isBase(true)
		                          .name("skinName")
		                          .description("skinDescription")
		                          .build()
		);
	}

	private CdragonTacticalInfo createTacticalInfo() {
		return CdragonTacticalInfo.builder()
		                          .style(1)
		                          .difficulty(5)
		                          .damageType("damageType")
		                          .build();
	}

	private CdragonPlaystyleInfo createPlaystyleInfo() {
		return CdragonPlaystyleInfo.builder()
		                           .crowdControl(1)
		                           .utility(2)
		                           .damage(3)
		                           .mobility(4)
		                           .durability(5)
		                           .build();
	}

	private CdragonPassive createPassive() {
		return CdragonPassive.builder()
		                     .name("name")
		                     .description("description")
		                     .build();
	}

}