package de.htwberlin.webtech.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testGettersAndSetters() {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");
        task.setCompleted(true);

        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getDescription());
        assertTrue(task.isCompleted());
    }

    @Test
    void testConstructor() {
        Task task = new Task(1L, "Test Task", true);
        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getDescription());
        assertTrue(task.isCompleted());
    }

    @Test
    void testToString() {
        Task task = new Task(1L, "Test Task", false);
        String expected = "Task(id=1, description=Test Task, completed=false)";
        assertEquals(expected, task.toString());
    }
}
