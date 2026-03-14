package com.gabriel.taskmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gabriel.taskmanager.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
