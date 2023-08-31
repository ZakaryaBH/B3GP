package com.grantburgess.database.jpa;

import com.grantburgess.database.jpa.repositories.FeatureRepository;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.database.FeatureGateway;
import com.grantburgess.ports.database.TaskGateway;

public class JpaDatabase implements Database {
    private TaskGateway taskGateway;
    private FeatureGateway featureGateway;

    public JpaDatabase(TaskRepository taskRepository, FeatureRepository featureRepository) {
        taskGateway = new JpaTaskGateway(taskRepository);
        featureGateway = new JpaFeatureGateway(featureRepository);
    }


    public TaskGateway taskGateway() {
        return taskGateway;
    }
    public FeatureGateway featureGateway() {
        return featureGateway;
    }
}
