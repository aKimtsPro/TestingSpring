package be.akimts.test.testingspring.api.controller;

import be.akimts.test.testingspring.api.model.dto.TaskDTO;
import be.akimts.test.testingspring.api.model.form.TaskForm;
import be.akimts.test.testingspring.bll.TaskService;
import be.akimts.test.testingspring.domain.entities.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskForm task) {
        Task createdTask = taskService.create(task.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( TaskDTO.mapToDTO(createdTask) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body( TaskDTO.mapToDTO(taskService.getOne(id)) );
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok()
                .body(
                        tasks.stream()
                                .map(TaskDTO::mapToDTO)
                                .toList()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskForm task) {
        Task updatedTask = taskService.update(id, task.toEntity());
        return ResponseEntity.ok().body(TaskDTO.mapToDTO(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
