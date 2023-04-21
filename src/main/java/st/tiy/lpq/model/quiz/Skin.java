package st.tiy.lpq.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Skin {

	@Id
	@GeneratedValue
	private Long id;

	private Integer number;
	private String name;
	private boolean hasChromas;
	private byte[] splash;
	private String championName;
	private int championId;

}
