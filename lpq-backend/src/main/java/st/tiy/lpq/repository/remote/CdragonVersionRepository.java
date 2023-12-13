package st.tiy.lpq.repository.remote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.CdragonVersion;

@Repository
public interface CdragonVersionRepository extends JpaRepository<CdragonVersion, Long> {

    CdragonVersion findTopByOrderByIdDesc();

}
