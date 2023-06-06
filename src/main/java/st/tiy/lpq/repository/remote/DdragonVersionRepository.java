package st.tiy.lpq.repository.remote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import st.tiy.lpq.model.quiz.DdragonVersion;

@Repository
public interface DdragonVersionRepository extends JpaRepository<DdragonVersion, Long> {

    DdragonVersion findTopByOrderByIdDesc();

}
