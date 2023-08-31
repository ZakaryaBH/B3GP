package com.grantburgess.database.inmemory;

import com.grantburgess.entities.Task;
import com.grantburgess.ports.database.TaskGateway;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTaskGateway implements TaskGateway {
    private final Map<String, Task> offers = new HashMap<>();

    public Collection<Task> getAllExcludingCancelled() {
        return Collections.unmodifiableList(
                offers.values()
                        .stream()
//                        .filter(InMemoryOfferGateway::excludeCancelledOffers)
                        .collect(Collectors.toList())
        );
    }

//    private static boolean excludeCancelledOffers(Task offer) {
//        return offer.getCancelDate() == null;
//    }

    public UUID add(Task offer) {
        UUID uuid = UUID.randomUUID();
        offers.put(uuid.toString(), new Task(uuid, offer.getName(), offer.getDescription(), offer.getStartDate(), offer.getEndDate()));

        return uuid;
    }

    public Task getByIdExcludingCancelled(UUID id) {
        Task offer = offers.get(id.toString());
        if (offer == null)
            return null;

        return new Task(offer.getId(), offer.getName(), offer.getDescription(), offer.getStartDate(), offer.getEndDate());
    }

    public UUID update(Task offer) {
        offers.put(offer.getId().toString(), offer);
        return null;
    }

    public boolean existsByName(String name) {
        return getAllExcludingCancelled()
                .stream()
                .anyMatch(offer -> offer.getName().equalsIgnoreCase(name));
    }
}
