package be.akimts.test.testingspring.api.model.form;

import be.akimts.test.testingspring.domain.entities.Task;
import be.akimts.test.testingspring.domain.entities.TaskStatus;
import lombok.Data;

@Data
public class TaskForm {

    private TaskStatus status;
    private String title;
    private String description;


    // Mapper method to convert TaskForm to Task entity
    public Task toEntity() {
        Task task = new Task();
        task.setStatus(this.status);
        task.setTitle(this.title);
        task.setDescription(this.description);
        return task;
    }
}
