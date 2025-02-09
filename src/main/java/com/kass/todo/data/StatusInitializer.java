package com.kass.todo.data;

import com.kass.todo.models.StatusModel;
import com.kass.todo.repositories.IStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StatusInitializer implements CommandLineRunner {

    private final IStatus iStatus;

    public StatusInitializer(IStatus iStatus){
        this.iStatus = iStatus;
    }

    @Override
    public void run (String... args){

        if(iStatus.count() == 0){
            iStatus.save(new StatusModel("To Do"));
            iStatus.save(new StatusModel("In Progress"));
            iStatus.save(new StatusModel("Done"));
        }
    }
}
