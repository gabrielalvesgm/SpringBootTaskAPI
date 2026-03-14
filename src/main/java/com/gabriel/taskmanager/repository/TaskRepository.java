package com.gabriel.taskmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gabriel.taskmanager.entity.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface  TaskRepository extends JpaRepository<Task, Long> {

}
