package com.spring.repo;

import com.spring.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, String> {

    List<Task> findByIsOffline(boolean offline);
}
