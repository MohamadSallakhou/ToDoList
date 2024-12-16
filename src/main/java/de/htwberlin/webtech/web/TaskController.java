package de.htwberlin.webtech.web;
import org.springframework.http.ResponseEntity;


import de.htwberlin.webtech.model.Task;
import de.htwberlin.webtech.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Task>> getTasks(@RequestParam final Optional<Boolean> completed) {
        final Iterable<Task> result = completed.isEmpty()
                ? taskService.getAllTasks()
                : taskService.getTasksByCompletion(completed.get());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") final Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Task> addTask(@Valid @RequestBody final Task task) {
        final Task created = taskService.addTask(task.getDescription());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") final Long id, @RequestBody Task body) {
        body.setId(id);
        final Task updatedTask = taskService.updateTask(id, body);
        return updatedTask == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") final Long id) {
        return taskService.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

