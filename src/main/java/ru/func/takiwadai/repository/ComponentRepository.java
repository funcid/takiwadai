package ru.func.takiwadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.func.takiwadai.entity.component.Component;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
}
