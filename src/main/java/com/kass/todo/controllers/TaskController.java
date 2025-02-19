package com.kass.todo.controllers;

import com.kass.todo.dto.TaskDto;
import com.kass.todo.exceptions.NotFoundException;
import com.kass.todo.models.StatusModel;
import com.kass.todo.models.TaskModel;
import com.kass.todo.services.CategoryService;
import com.kass.todo.services.StatusService;
import com.kass.todo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            return getValidationErrors(bindingResult);
        }
        if (categoryExist(taskModel.getCategory().getId())) {
            throw new NotFoundException("Category not found");
        }
        if(taskModel.getStatus() == null){
            StatusModel statusModel = new StatusModel();
            taskModel.setStatus(statusModel);
        }
        taskModel.getStatus().setId(1);
        return new ResponseEntity<>(taskService.createTask(taskModel),HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@Valid @RequestBody TaskModel taskModel, BindingResult bindingResult, @PathVariable int id){
        if(bindingResult.hasErrors()){
            return getValidationErrors(bindingResult);
        }
        if (categoryExist(taskModel.getCategory().getId())) {
            throw new NotFoundException("Category not found");
        }
        TaskModel updatedTask = taskService.updateTask(id,taskModel);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateTaskStatus(@Valid @RequestBody StatusModel statusModel,BindingResult bindingResult , @PathVariable int id){
        if (!statusService.existStatus(statusModel.getId())) {
            throw new NotFoundException("Status not found");
        }
        if(bindingResult.hasErrors()){
            return getValidationErrors(bindingResult);
        }
        TaskModel updatedTask = taskService.updateStatus(id,statusModel);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter/{id}")
    public List<TaskDto> tasksByStatus(@PathVariable int id){
        List<TaskModel> tasks = taskService.getTasksByStatus(id) ;
        return  tasks.stream()
                .map(this::convertTaskToDto)
                .collect(Collectors.toList());
    }

    public TaskDto convertTaskToDto(TaskModel task){
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setCategoryName(task.getCategory().getName());
        taskDto.setCategoryColor(task.getCategory().getColor());
        return taskDto;

    }


    private boolean categoryExist(int id){
        return !categoryService.existCategory(id);
    }
    private ResponseEntity<Object> getValidationErrors(BindingResult bindingResult){
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return new ResponseEntity<>(errorMessages,HttpStatus.BAD_REQUEST);
    }





}
