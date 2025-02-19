package com.kass.todo.controllers;

import com.kass.todo.models.StatusModel;
import com.kass.todo.services.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController (StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusModel> getStatus() {
        return statusService.getAll();
    }

    @GetMapping("/{id}")
    public StatusModel getStatusById(@PathVariable int id) {
        return statusService.getStatusById(id);
    }

}
