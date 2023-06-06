package st.tiy.lpq.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
public class PlaystyleInfo {

	@Id
	@GeneratedValue
	private Long id;

	private Integer damage;
	private Integer durability;
	private Integer crowdControl;
	private Integer mobility;
	private Integer utility;

	@OneToOne
	private Champion champion;
}
