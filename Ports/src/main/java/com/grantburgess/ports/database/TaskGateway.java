package com.grantburgess.ports.database;

import com.grantburgess.entities.Task;

import java.util.Collection;
import java.util.UUID;

public interface TaskGateway {
    Collection<Task> getAllExcludingCancelled();
    UUID add(Task offer);
    Task getByIdExcludingCancelled(UUID id);
    UUID update(Task offer);
    UUID remove(UUID id);
    boolean existsByName(String name);

    public interface BadRequest {}
    public interface NotFound {}

    public class OfferNameAlreadyExistsException extends RuntimeException implements BadRequest {
    }

    public class OfferStartDateGreaterThanEndDateException extends RuntimeException implements BadRequest {
    }

    public class OfferNotFoundException extends RuntimeException implements NotFound {
    }

    public class CannotCancelOfferThatHasExpiredException extends RuntimeException implements BadRequest {
    }

    public class OfferEndDateCannotBeBeforeCurrentSystemDateException extends RuntimeException implements BadRequest {
    }
}
