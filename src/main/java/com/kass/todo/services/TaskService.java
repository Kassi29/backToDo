package com.kass.todo.services;

import com.kass.todo.exceptions.NotFoundException;
import com.kass.todo.models.CategoryModel;
import com.kass.todo.models.StatusModel;
import com.kass.todo.models.TaskModel;
import com.kass.todo.repositories.ITask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final ITask iTask;


    public TaskService(ITask iTask){
        this.iTask = iTask;
    }

    @Transactional(readOnly = true)
    public List<TaskModel> getAllTask(){
        return iTask.findAll();
    }

    @Transactional(readOnly = true)
    public TaskModel getTaskById(int id){
        return iTask.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @Transactional
    public TaskModel createTask(TaskModel taskModel){
        return iTask.save(taskModel);
    }

    @Transactional
    public TaskModel updateTask(int id , TaskModel taskModel){
        TaskModel existingTask = getTaskById(id);

        existingTask.setDescription(taskModel.getDescription());
        existingTask.setName(taskModel.getName());
        existingTask.setCategory(taskModel.getCategory());
        return iTask.save(existingTask);
    }

    @Transactional
    public TaskModel updateStatus(int id, StatusModel statusModel){
        TaskModel existingTask = getTaskById(id);

        existingTask.setStatus(statusModel);
        return iTask.save(existingTask);
    }


    @Transactional
    public void deleteTask(int id){
        TaskModel existingTask = getTaskById(id);
        iTask.delete(existingTask);
    }



}
