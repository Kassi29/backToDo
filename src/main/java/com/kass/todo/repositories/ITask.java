package com.kass.todo.repositories;

import com.kass.todo.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITask extends JpaRepository<TaskModel, Integer> {
}
