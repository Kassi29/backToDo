package com.kass.todo.controllers;

import com.kass.todo.models.CategoryModel;
import com.kass.todo.models.StatusModel;
import com.kass.todo.models.TaskModel;
import com.kass.todo.services.CategoryService;
import com.kass.todo.services.StatusService;
import com.kass.todo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final StatusService statusService;
    private final CategoryService categoryService;
    public TaskController(TaskService taskService,StatusService statusService,CategoryService categoryService){
       this.taskService = taskService;
       this.statusService = statusService;
       this.categoryService = categoryService;
    }

    @GetMapping
    public List<TaskModel> getAll(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable int id){
        TaskModel task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Object> createTask(@Valid @RequestBody TaskModel taskModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        categoryExist(taskModel.getCategory().getId());
        statusExist(taskModel.getStatus().getId());
        return new ResponseEntity<>(taskService.createTask(taskModel),HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable int id, @RequestBody TaskModel taskModel){
        TaskModel updatedTask = taskService.updateTask(id,taskModel);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<TaskModel> updateTaskStatus(@PathVariable int id, @RequestBody StatusModel statusModel){
        statusExist(statusModel.getId());
        TaskModel updatedTask = taskService.updateStatus(id,statusModel);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<TaskModel> updateTaskCategory(@PathVariable int id, @RequestBody CategoryModel categoryModel){
        TaskModel updatedTask = taskService.updateCategory(id,categoryModel);
        categoryExist(categoryModel.getId());
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void statusExist(int id){
        statusService.getStatusById(id);
    }
    private void categoryExist(int id){
        categoryService.getCategoryById(id);
    }



}
