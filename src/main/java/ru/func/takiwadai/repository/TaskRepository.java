package ru.func.takiwadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.func.takiwadai.entity.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
