package com.kass.todo.repositories;

import com.kass.todo.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatus extends JpaRepository<StatusModel,Integer> {
}
