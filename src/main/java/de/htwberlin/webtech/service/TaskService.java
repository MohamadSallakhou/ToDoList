package de.htwberlin.webtech.service;

import de.htwberlin.webtech.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new HashMap<>(Map.of(
            1L, new Task(1L, "Einkaufen gehen", false),
            2L, new Task(2L, "Hausaufgaben erledigen", true),
            3L, new Task(3L, "Mit Freunden treffen", false)
    ));
    private long nextId = 4;

    // Abrufen aller Aufgaben
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // Abrufen einer Aufgabe anhand der ID
    public Optional<Task> getTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    // Hinzufügen einer neuen Aufgabe
    public Task addTask(String description) {
        Task newTask = new Task(nextId++, description, false);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    // Aktualisieren einer bestehenden Aufgabe
    public Task updateTask(Long id, Task updatedTask) {
        if (!tasks.containsKey(id)) return null;
        updatedTask.setId(id);
        tasks.put(id, updatedTask);
        return updatedTask;
    }

    // Löschen einer Aufgabe
    public boolean deleteTask(Long id) {
        return tasks.remove(id) != null;
    }

    // Abrufen von Aufgaben nach Status
    public List<Task> getTasksByCompletion(boolean completed) {
        return tasks.values().stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());
    }
}
