package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
class TodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
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
				task.setCompleted(updatedTask.isCompleted());
				return task;
			}
		}
		return null;
	}
}

@Controller
@RequestMapping("/tasks")
class TodoViewController {
	private final List<Task> tasks = new ArrayList<>();
	private final AtomicLong counter = new AtomicLong();

	@GetMapping
	public String getAllTasks(Model model) {
		model.addAttribute("tasks", tasks);
		return "todo";  // Thymeleaf sucht nach einer Datei mit dem Namen "todo.html" in /resources/templates
	}

	@PostMapping("/addTask")
	public String addTask(String description) {
		Task newTask = new Task();
		newTask.setId(counter.incrementAndGet());
		newTask.setDescription(description);
		newTask.setCompleted(false);
		tasks.add(newTask);
		return "redirect:/tasks";  // Nach dem Hinzufügen einer Aufgabe wird die Liste neu geladen
	}
}




@Setter
@Getter
class Task {
	private Long id;
	private String description;
	private boolean completed;
}
