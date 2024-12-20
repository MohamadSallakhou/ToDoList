//package de.htwberlin.webtech.service;
//
//import de.htwberlin.webtech.model.Task;
//import de.htwberlin.webtech.repository.TaskRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TaskServiceTest {
//
//    @Mock
//    private TaskRepository taskRepository;
//
//    @InjectMocks
//    private TaskService taskService;
//
//    @BeforeEach
//    void setUpMockRepository() {
//        MockitoAnnotations.openMocks(this);
//
//        // Mock-Daten
//        Task task1 = new Task(1L, "Task 1", false);
//        Task task2 = new Task(2L, "Task 2", true);
//
//        // Mock-Verhalten
//        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));
//        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
//        when(taskRepository.findByCompleted(true)).thenReturn(List.of(task2));
//    }
//
//    @Test
//    void testGetAllTasks() {
//        // Act
//        List<Task> tasks = taskService.getAllTasks();
//
//        // Assert
//        assertNotNull(tasks);
//        assertEquals(2, tasks.size());
//        assertEquals("Task 1", tasks.get(0).getDescription());
//        assertEquals("Task 2", tasks.get(1).getDescription());
//        verify(taskRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetTaskById() {
//        // Act
//        Task task = taskService.getTaskById(1L);
//
//        // Assert
//        assertNotNull(task);
//        assertEquals(1L, task.getId());
//        assertEquals("Task 1", task.getDescription());
//        verify(taskRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testGetTasksByCompletion() {
//        // Act
//        List<Task> completedTasks = taskService.getTasksByCompletion(true);
//
//        // Assert
//        assertNotNull(completedTasks);
//        assertEquals(1, completedTasks.size());
//        assertEquals("Task 2", completedTasks.get(0).getDescription());
//        verify(taskRepository, times(1)).findByCompleted(true);
//    }
//}
