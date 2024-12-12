package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
@RequestMapping("/api/tasks")
class TodoController {
	private final List<Task> tasks = new ArrayList<>();
	private final AtomicLong counter = new AtomicLong();

	@GetMapping
	public List<Task> getAllTasks() {
		return tasks;
	}

	@PostMapping
	public Task addTask(@RequestBody Task newTask) {
		newTask.setId(counter.incrementAndGet());
		tasks.add(newTask);
		return newTask;
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {
		tasks.removeIf(task -> task.getId().equals(id));
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
		for (Task task : tasks) {
			if (task.getId().equals(id)) {
				task.setDescription(updatedTask.getDescription());
				task.setCompleted(updatedTask.isCompleted());
				return task;
			}
		}
		return null;
	}
}

@Setter
@Getter
class Task {
	private Long id;
	private String description;
	private boolean completed;
}
