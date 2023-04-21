package st.tiy.lpq.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Champion {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String title;
	private List<String> tags;
	private String resource; //TODO move to enum ?
	private String lore;
	private List<Spell> spells;
	private List<Skin> skins;

}
