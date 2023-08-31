package com.grantburgess.database.jpa;

import com.grantburgess.database.jpa.data.TaskData;
import com.grantburgess.database.jpa.repositories.TaskRepository;
import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JpaTaskGateway implements TaskGateway {
    private final TaskRepository taskRepository;

    public Collection<Task> getAllExcludingCancelled() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToTask)
                .collect(Collectors.toList());
    }

    public JpaTaskGateway(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task mapToTask(TaskData taskData) {
        return new Task(
                taskData.getId(),
                taskData.getName(),
                taskData.getDescription(),
                taskData.getStartDate(),
                taskData.getEndDate()
        );
    }

    public UUID add(Task offer) {
        UUID id = UUID.randomUUID();
        TaskData taskData = TaskData
                .builder()
                .id(id)
                .name(offer.getName())
                .description(offer.getDescription())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .build();
        taskRepository.save(taskData);

        return id;
    }

    public Task getByIdExcludingCancelled(UUID id) {
        return taskRepository.findById(id)
                .map(this::mapToTask)
                .orElse(null);
    }

    public UUID update(Task offer) {
        TaskData taskData = TaskData
                .builder()
                .id(offer.getId())
                .name(offer.getName())
                .description(offer.getDescription())
                .startDate(offer.getStartDate())
                .endDate(offer.getEndDate())
                .build();

        taskRepository.save(taskData);
        return offer.getId();
    }

    public UUID remove(UUID id) {
        taskRepository.deleteById(id);
        return id;
    }

    public boolean existsByName(String name) {
        return taskRepository.existsByName(name);
    }
}
