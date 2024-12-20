package de.htwberlin.webtech.service;

import de.htwberlin.webtech.repository.TaskRepository;
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
    private TaskRepository taskRepository;

    //private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();


    public Task addTask(String description) {
        Task newTask = new Task();
        newTask.setId(counter.incrementAndGet());
        newTask.setDescription(description);
        newTask.setCompleted(false);
        return createTask(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public List<Task> getTasksByCompletion(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        if (taskRepository.existsById(id)) {
            task.setId(id); return taskRepository.save(task);
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        taskRepository.deleteById(id);
        return getTaskById(id) == null;
    }

}