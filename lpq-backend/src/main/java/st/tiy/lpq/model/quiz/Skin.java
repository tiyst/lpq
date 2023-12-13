package st.tiy.lpq.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class Skin {

	@Id
	@GeneratedValue
	private Long id;

	private Integer number;
	private String name;
	private boolean hasChromas;
	@ManyToOne
	private Champion champion;

}
