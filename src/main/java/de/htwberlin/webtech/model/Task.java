package de.htwberlin.webtech.model;

import lombok.Getter;
import lombok.Setter;

// Task-Klasse
@Setter
@Getter
public class Task {
    private Long id;
    private String description;
    private boolean completed;
}
