package be.akimts.test.testingspring.bll.impl;

import be.akimts.test.testingspring.bll.TaskService;
import be.akimts.test.testingspring.bll.exceptions.ResourceNotFoundException;
import be.akimts.test.testingspring.bll.exceptions.TitleAlreadyTakenException;
import be.akimts.test.testingspring.dal.TaskRepository;
import be.akimts.test.testingspring.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if( taskRepository.existsByTitle(task.getTitle()) )
            throw new TitleAlreadyTakenException();

        return taskRepository.save(task);
    }

    @Override
    public Task getOne(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(long id, Task task) {
        Task toUpdate = this.getOne(id);

        toUpdate.setDescription(toUpdate.getDescription());
        toUpdate.setStatus(toUpdate.getStatus());
        toUpdate.setTitle(toUpdate.getTitle());

        return taskRepository.save(toUpdate);
    }

    @Override
    public void delete(long id) {
        Task toUpdate = this.getOne(id);

        taskRepository.delete(toUpdate);
    }

    @Override
    public long count() {
        return taskRepository.count();
    }
}
