package com.kass.todo.repositories;

import com.kass.todo.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITask extends JpaRepository<TaskModel, Integer> {
    List<TaskModel> findAllByStatusId(int statusId);
}
