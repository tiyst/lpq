package st.tiy.lpq.model.quiz;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Champion {

	@Id
	@GeneratedValue
	private Long id;
	private Integer riotId;

	private String name;
	private String alias;
	private String title;

	@ElementCollection
	private List<String> roles;
	private String resource; //TODO move to enum ?
	private String lore;
	@OneToOne
	private TacticalInfo tacticalInfo;
	@OneToOne
	private PlaystyleInfo playstyleInfo;
	@OneToOne
	private Passive passive;
	@OneToMany(mappedBy = "champion")
	private List<Spell> spells;
	@OneToMany(mappedBy = "champion")
	private List<Skin> skins;

}
