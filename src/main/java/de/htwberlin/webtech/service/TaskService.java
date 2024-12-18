package de.htwberlin.webtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import de.htwberlin.webtech.model.Task;


@Service
public class TaskService {
    @Autowired
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Task addTask(String description) {
        Task newTask = new Task();
        newTask.setId(counter.incrementAndGet());
        newTask.setDescription(description);
        newTask.setCompleted(false);
        tasks.add(newTask);
        return newTask;
    }

    public Task updateTask(Long id, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());
                return task;
            }
        }
        return null; // Task mit der ID existiert nicht
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    public Optional<Task> getTaskById(Long id) { // Geändert von Boolean zu Long
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public List<Task> getTasksByCompletion(boolean completed) {
        return tasks.stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());
    }
}
