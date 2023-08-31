package com.grantburgess.application.configuration;

import com.grantburgess.database.jpa.JpaDatabase;
import com.grantburgess.database.jpa.repositories.FeatureRepository;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.ports.database.Database;
import com.grantburgess.ports.presenters.feature.*;
import com.grantburgess.ports.presenters.task.*;
import com.grantburgess.ports.usescases.Clock;
import com.grantburgess.ports.usescases.feature.addfeature.AddFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.get.featurebyid.GetFeatureByIdInputBoundary;
import com.grantburgess.ports.usescases.feature.get.features.GetFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.removeFeature.RemoveFeatureInputBoundary;
import com.grantburgess.ports.usescases.feature.updateFeature.UpdateFeatureInputBoundary;
import com.grantburgess.ports.usescases.task.addtask.AddTaskInputBoundary;
import com.grantburgess.ports.usescases.task.get.taskbyid.GetTaskByIdInputBoundary;
import com.grantburgess.ports.usescases.task.get.tasks.GetTaskInputBoundary;
import com.grantburgess.ports.usescases.task.removeTask.RemoveTaskInputBoundary;
import com.grantburgess.ports.usescases.task.updateTask.UpdateTaskInputBoundary;
import com.grantburgess.presenters.feature.*;
import com.grantburgess.presenters.task.*;
import com.grantburgess.usecases.feature.addfeature.AddFeature;
import com.grantburgess.usecases.feature.get.featurebyid.GetFeatureById;
import com.grantburgess.usecases.feature.get.features.GetFeatures;
import com.grantburgess.usecases.feature.removefeature.RemoveFeature;
import com.grantburgess.usecases.feature.updatefeature.UpdateFeature;
import com.grantburgess.usecases.task.addtask.AddTask;
import com.grantburgess.usecases.task.get.taskbyid.GetTaskById;
import com.grantburgess.usecases.task.get.tasks.GetTasks;
import com.grantburgess.usecases.task.removetask.RemoveTask;
import com.grantburgess.usecases.task.updatetask.UpdateTask;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@Configuration
@EntityScan("com.grantburgess.database.jpa.data")
@EnableJpaRepositories("com.grantburgess.database.jpa.repositories")
public class ApplicationConfiguration {
    @Bean
    public Database database(TaskRepository taskRepository, FeatureRepository featureRepository) {
        return new JpaDatabase(taskRepository,featureRepository);
    }

    @Bean
    public Clock clock() {
        return new Clock() {
            @Override
            public LocalDate now() {
                return LocalDate.now();
            }
        };
    }

    @Bean
    public GetTaskInputBoundary getTaskInputBoundary(TasksOutputBoundary tasksOutputBoundary, Database database, Clock clock) {
        return new GetTasks(tasksOutputBoundary, database.taskGateway(), clock);
    }

    @Bean
    public TasksOutputBoundary tasksOutputBoundary() {
        return new TasksPresenter();
    }

    @Bean
    public GetTaskByIdInputBoundary getTaskByIdInputBoundary(TaskOutputBoundary taskOutputBoundary, Database database, Clock clock) {
        return new GetTaskById(taskOutputBoundary, database.taskGateway(), clock);
    }

    @Bean
    public TaskOutputBoundary taskOutputBoundary() {
        return new TaskPresenter();
    }

    @Bean
    public AddTaskInputBoundary addTaskInputBoundary(TaskCreatedOutputBoundary taskCreatedOutputBoundary, Database database, Clock clock) {
        return new AddTask(taskCreatedOutputBoundary, database.taskGateway(), clock);
    }

    @Bean
    public TaskCreatedOutputBoundary taskCreatedOutputBoundary() {
        return new TaskCreatedPresenter();
    }

    @Bean
    public TaskUpdatedOutputBoundary TaskUpdatedOutputBoundary() {
        return new TaskUpdatedPresenter();
    }

    @Bean
    public UpdateTaskInputBoundary UpdateTaskInputBoundary(TaskUpdatedOutputBoundary taskUpdatedOutputBoundary, Database database, Clock clock) {
        return new UpdateTask(taskUpdatedOutputBoundary, database.taskGateway(), clock);
    }

    @Bean
    public RemoveTaskInputBoundary RemoveTaskInputBoundary(TaskRemovedOutputBoundary taskremovedOutputBoundary, Database database, Clock clock) {
        return new RemoveTask(taskremovedOutputBoundary, database.taskGateway(), clock);
    }

    @Bean
    public TaskRemovedOutputBoundary taskRemovedOutputBoundary() {
        return new TaskRemovedPresenter();
    }

    @Bean
    public GetFeatureInputBoundary getFeatureInputBoundary(FeaturesOutputBoundary featuresOutputBoundary, Database database, Clock clock) {
        return new GetFeatures(featuresOutputBoundary, database.featureGateway(), clock);
    }

    @Bean
    public FeaturesOutputBoundary featuresOutputBoundary() {
        return new FeaturesPresenter();
    }

    @Bean
    public GetFeatureByIdInputBoundary getFeatureByIdInputBoundary(FeatureOutputBoundary featureOutputBoundary, Database database, Clock clock) {
        return new GetFeatureById(featureOutputBoundary, database.featureGateway(), clock);
    }

    @Bean
    public FeatureOutputBoundary featureOutputBoundary() {
        return new FeaturePresenter();
    }

    @Bean
    public AddFeatureInputBoundary addFeatureInputBoundary(FeatureCreatedOutputBoundary featureCreatedOutputBoundary, Database database, Clock clock) {
        return new AddFeature(featureCreatedOutputBoundary, database.featureGateway(), clock);
    }

    @Bean
    public FeatureCreatedOutputBoundary featureCreatedOutputBoundary() {
        return new FeatureCreatedPresenter();
    }

    @Bean
    public FeatureUpdatedOutputBoundary FeatureUpdatedOutputBoundary() {
        return new FeatureUpdatedPresenter();
    }

    @Bean
    public UpdateFeatureInputBoundary UpdateFeatureInputBoundary(FeatureUpdatedOutputBoundary featureUpdatedOutputBoundary, Database database, Clock clock) {
        return new UpdateFeature(featureUpdatedOutputBoundary, database.featureGateway(), clock);
    }

    @Bean
    public RemoveFeatureInputBoundary RemoveFeatureInputBoundary(FeatureRemovedOutputBoundary featureremovedOutputBoundary, Database database, Clock clock) {
        return new RemoveFeature(featureremovedOutputBoundary, database.featureGateway(), clock);
    }

    @Bean
    public FeatureRemovedOutputBoundary featureRemovedOutputBoundary() {
        return new FeatureRemovedPresenter();
    }

}
