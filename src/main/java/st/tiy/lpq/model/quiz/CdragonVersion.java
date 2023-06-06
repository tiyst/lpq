package st.tiy.lpq.model.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CdragonVersion {

    @Id
    @GeneratedValue
    private Long id;
    private String version;

}
