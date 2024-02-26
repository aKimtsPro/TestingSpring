package be.akimts.test.testingspring.dal;

import be.akimts.test.testingspring.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTitle(String title);
}
