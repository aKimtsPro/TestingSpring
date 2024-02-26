package be.akimts.test.testingspring.bll;

import be.akimts.test.testingspring.domain.entities.Task;

import java.util.List;

public interface TaskService {

    /**
     * registers the given task in the database
     *
     * @param task
     * @return the added task
     */
    Task create(Task task);

    Task getOne(Long id);
    List<Task> getAll();

    Task update(long id, Task task);

    void delete(long id);

    long count();
}
