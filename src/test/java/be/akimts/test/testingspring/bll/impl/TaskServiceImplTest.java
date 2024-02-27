package be.akimts.test.testingspring.bll.impl;

import be.akimts.test.testingspring.bll.TaskService;
import be.akimts.test.testingspring.bll.exceptions.ResourceNotFoundException;
import be.akimts.test.testingspring.bll.exceptions.TitleAlreadyTakenException;
import be.akimts.test.testingspring.dal.TaskRepository;
import be.akimts.test.testingspring.domain.entities.Task;
import be.akimts.test.testingspring.domain.entities.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    private TaskService taskService;
    private TaskRepository mockRepo;

    @BeforeEach
    void initTest(){
        this.mockRepo = mock(TaskRepository.class);
//        this.mockRepo = Mockito.mock(TaskRepository.class); // pareil que ceci grace à l'import static.
        this.taskService = new TaskServiceImpl(this.mockRepo);
    }

    @Test
    void create() {
        Task toCreate = new Task();
        toCreate.setTitle("titre");
        toCreate.setDescription("description");
        toCreate.setStatus(TaskStatus.TODO);

        when( mockRepo.save(toCreate) ).thenReturn(toCreate);

        Task rslt = taskService.create(toCreate);
        assertEquals(toCreate, rslt);
        verify( mockRepo, times(1)).save(toCreate);
    }

    @Test
    void create_titleTaken() {
        Task task = new Task();
        task.setTitle("taken");

        when( mockRepo.existsByTitle("taken") ).thenReturn(true);
        assertThrows(TitleAlreadyTakenException.class, () -> this.taskService.create(task));
        verify( mockRepo, times(1) ).existsByTitle("taken");
    }

    @Test
    void getOne() {
        Task task = new Task();
        when( mockRepo.findById(1L) )
                .thenReturn( Optional.of(task) );

        Task result = this.taskService.getOne(1L);
        assertEquals(task, result);
        verify( mockRepo ).findById(1L);
    }

    @Test
    void getOne_notFound(){
        when( mockRepo.findById(1L) )
                .thenReturn( Optional.empty() );

        assertThrows( ResourceNotFoundException.class, () -> this.taskService.getOne(1L) );
        verify( mockRepo, times(2)).findById(1L);
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("title");
        task.setDescription("desc");
        task.setStatus(TaskStatus.TODO);

        when( mockRepo.findById(1L) ).thenReturn(Optional.of(task));
        taskService.delete(1L);
        verify(mockRepo, times(1)).delete(task);
    }

    @Test
    void delete_notFound(){
        when( mockRepo.findById(1L) ).thenReturn(Optional.empty());
        assertThrows( ResourceNotFoundException.class, () -> taskService.delete(1L) );
    }

    @Test
    void count() {
    }
}
