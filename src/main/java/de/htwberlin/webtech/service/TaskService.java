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

    // public List<Task> getAllTasks() {
    //    return new ArrayList<>(tasks);
    //}

    //public Task addTask(String description) {
    //    Task newTask = new Task();
    //    newTask.setId(counter.incrementAndGet());
    //    newTask.setDescription(description);
    //    newTask.setCompleted(false);
    //    tasks.add(newTask);
    //    return newTask;
    //}

   // public Task updateTask(Long id, Task updatedTask) {
   //     for (Task task : tasks) {
   //         if (task.getId().equals(id)) {
   //             task.setDescription(updatedTask.getDescription());
   //             task.setCompleted(updatedTask.isCompleted());
   //             return task;
   //         }
   //     }
   //     return null; // Task mit der ID existiert nicht
   // }

    //public boolean deleteTask(Long id) {
    //    return tasks.removeIf(task -> task.getId().equals(id));
    //}

    //public Optional<Task> getTaskById(Long id) { // Geändert von Boolean zu Long
    //    return tasks.stream()
    //            .filter(task -> task.getId().equals(id))
    //            .findFirst();
    //}

    //public List<Task> getTasksByCompletion(boolean completed) {
    //    return tasks.stream()
    //            .filter(task -> task.isCompleted() == completed)
    //            .collect(Collectors.toList());
    //}

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
