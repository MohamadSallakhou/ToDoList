package de.htwberlin.webtech;

import de.htwberlin.webtech.model.Task;
import de.htwberlin.webtech.service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Entry Point
@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.htwberlin.webtech.repository")
@EntityScan(basePackages = "de.htwberlin.webtech.model")
class TodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}


// View-Controller für die Web-Ansicht
@Controller
@RequestMapping("/tasks")
class TodoViewController {
	private final TaskService taskService;

	public TodoViewController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public String getAllTasks(Model model) {
		model.addAttribute("tasks", taskService.getAllTasks());
		return "todo"; // Thymeleaf sucht nach "todo.html"
	}

	@PostMapping("/addTask")
	public String addTask(String description) {
		taskService.addTask(description);
		return "redirect:/tasks"; // Nach dem Hinzufügen der Aufgabe wird die Seite neu geladen
	}
}

