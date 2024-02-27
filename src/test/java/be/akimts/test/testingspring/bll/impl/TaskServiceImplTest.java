package be.akimts.test.testingspring.bll.impl;

import be.akimts.test.testingspring.bll.TaskService;
import be.akimts.test.testingspring.bll.exceptions.ResourceNotFoundException;
import be.akimts.test.testingspring.dal.TaskRepository;
import be.akimts.test.testingspring.domain.entities.Task;
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
    }

    @Test
    void count() {
    }
}
