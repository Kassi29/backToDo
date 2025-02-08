package com.kass.todo.services;

import com.kass.todo.models.CategoryModel;
import com.kass.todo.models.StatusModel;
import com.kass.todo.models.TaskModel;
import com.kass.todo.repositories.ITask;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final ITask iTask;


    private TaskModel getExistingTask(int id){
        return iTask.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public TaskService(ITask iTask){
        this.iTask = iTask;
    }

    @Transactional(readOnly = true)
    public List<TaskModel> getAll(){
        return iTask.findAll();
    }

    @Transactional(readOnly = true)
    public TaskModel getById(int id){
        return getExistingTask(id);
    }

    @Transactional
    public TaskModel create(TaskModel taskModel){
        return iTask.save(taskModel);
    }

    @Transactional
    public TaskModel update (int id ,TaskModel taskModel){
        TaskModel existingTask = getExistingTask(id);

        existingTask.setCategory(taskModel.getCategory());
        existingTask.setDescription(taskModel.getDescription());
        existingTask.setName(taskModel.getName());
            return iTask.save(existingTask);
    }

    @Transactional
    public TaskModel updateStatus(int id, StatusModel statusModel){
        TaskModel existingTask = getExistingTask(id);

        existingTask.setStatus(statusModel);
        return iTask.save(existingTask);

    }

    @Transactional
    public TaskModel updateCategory(int id, CategoryModel categoryModel){
        TaskModel existingTask = getExistingTask(id);

        existingTask.setCategory(categoryModel);
        return iTask.save(existingTask);

    }

    @Transactional
    public void delete(int id){
        TaskModel existingTask = getExistingTask(id);
        iTask.delete(existingTask);
    }
}
