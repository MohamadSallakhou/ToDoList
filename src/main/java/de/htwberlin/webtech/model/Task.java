package de.htwberlin.webtech.model;


import lombok.*;

// Task-Klasse

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
