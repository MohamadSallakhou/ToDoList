package de.htwberlin.webtech.repository;

import de.htwberlin.webtech.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByCompleted(boolean completed);
}