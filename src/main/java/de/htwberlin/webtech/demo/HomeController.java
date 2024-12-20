package de.htwberlin.webtech.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("someData", "Value from Controller!"); // Fügt Daten zur View hinzu
        return "index"; // Name der View-Datei (ohne .html)
    }
}
