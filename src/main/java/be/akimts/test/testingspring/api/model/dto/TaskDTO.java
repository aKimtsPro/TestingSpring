package be.akimts.test.testingspring.api.model.dto;

import be.akimts.test.testingspring.domain.entities.Task;
import be.akimts.test.testingspring.domain.entities.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private TaskStatus status;
    private String title;
    private String description;

    // Static mapper method to convert Task entity to TaskDTO
    public static TaskDTO mapToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getStatus(),
                task.getTitle(),
                task.getDescription()
        );
    }
}
