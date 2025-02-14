package com.kass.todo.services;

import com.kass.todo.exceptions.NotFoundException;
import com.kass.todo.models.StatusModel;
import com.kass.todo.repositories.IStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatusService {
    private final IStatus iStatus;

    public StatusService(IStatus iStatus) {
        this.iStatus = iStatus;
    }

    @Transactional(readOnly = true)
    public List<StatusModel> getAll() {
        return iStatus.findAll();
    }

    @Transactional(readOnly = true)
    public StatusModel getStatusById(int id) {
        return iStatus.findById(id)
                .orElseThrow(() -> new NotFoundException("Status not found"));
    }

    public boolean existStatus(int id){
        return iStatus.existsById(id);
    }
}
