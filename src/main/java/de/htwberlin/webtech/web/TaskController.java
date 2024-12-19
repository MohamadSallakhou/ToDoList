package de.htwberlin.webtech.web;

import de.htwberlin.webtech.model.Task;
import de.htwberlin.webtech.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    // Abrufen aller Aufgaben, optional nach Status gefiltert
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Task>> getTasks(@RequestParam Optional<Boolean> completed) {
        final Iterable<Task> result = completed.isEmpty()
                ? taskService.getAllTasks()
                : taskService.getTasksByCompletion(completed.get());
        return ResponseEntity.ok(result);
    }

    // Abrufen einer spezifischen Aufgabe
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Hinzufügen einer neuen Aufgabe
    @PostMapping
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        final Task created = taskService.addTask(task.getDescription());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Aktualisieren einer bestehenden Aufgabe
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task body) {
        body.setId(id);
        final Task updatedTask = taskService.updateTask(id, body);
        return updatedTask == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedTask);
    }

    // Löschen einer Aufgabe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        return taskService.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // Beispielaufgabe abrufen
    @GetMapping("/example")
    public ResponseEntity<Task> getExampleTask() {
        Task exampleTask = new Task(0L, "Beispielaufgabe: Einkaufen gehen", false);
        return ResponseEntity.ok(exampleTask);
    }
}
