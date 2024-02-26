package be.akimts.test.testingspring.bll.impl;

import be.akimts.test.testingspring.bll.TaskService;
import be.akimts.test.testingspring.dal.TaskRepository;
import be.akimts.test.testingspring.domain.entities.Task;
import be.akimts.test.testingspring.domain.entities.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceImplTest {

    private TaskService taskService;
    private TaskRepository mockRepo;

    @BeforeEach
    void initTest(){
        this.mockRepo = mock(TaskRepository.class);
        this.taskService = new TaskServiceImpl(mockRepo); // parler du fait que @autowired fait chier ici
    }

    @Test
    void create() {
        Task task = new Task();
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");

        when(mockRepo.save(task)).thenReturn(task);
        var created = this.taskService.create(task);
        assertEquals(created, task);
        verify(mockRepo, times(1)).save(task);
    }

    @Test
    void create_titleTaken(){
        var task = new Task();
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");
        when( mockRepo.existsByTitle(task.getTitle()) ).thenReturn(true);
        assertThrows( RuntimeException.class, () -> this.taskService.create(task) );
    }

    @Test
    void getOne() {
        var task = new Task();
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(task));

        var gottenTask = this.taskService.getOne(1L);

        assertEquals(gottenTask, task);
        verify(mockRepo, times(1)).findById(1L);
    }

    @Test
    void getOne_notFound(){
        when(mockRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.taskService.getOne(1L));
    }
    @Test
    void getAll() {
        var task = new Task();
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");

        var mockList = List.of(task);

        when(mockRepo.findAll()).thenReturn( mockList );

        var taskList = this.taskService.getAll();

        assertEquals(taskList, mockList);
        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void update() {
        var task = new Task();
        task.setId(1L);
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(task));
        when(mockRepo.save(task)).thenReturn(task);
        assertEquals(task, this.taskService.update(1L, task));
        verify(mockRepo, times(1)).save(task);
    }

    @Test
    void delete() {
        var task = new Task();
        task.setId(1L);
        task.setStatus(TaskStatus.TODO);
        task.setTitle("todoo");
        task.setDescription("desc");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(task));

        taskService.delete(1L);
        verify(mockRepo, times(1)).delete(task);
    }

    @Test
    void count() {
        when(mockRepo.count()).thenReturn(0L);

        assertEquals( taskService.count(), 0L);

        verify(mockRepo, times(1)).count();
    }
}
