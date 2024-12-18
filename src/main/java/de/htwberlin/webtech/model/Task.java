package de.htwberlin.webtech.model;

import jakarta.persistence.Entity;
import lombok.*;

// Task-Klasse
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String description;
    private boolean completed;
}
