package com.grantburgess.ports.database;

import com.grantburgess.entities.Feature;

import java.util.Collection;
import java.util.UUID;

public interface FeatureGateway {
    Collection<Feature> getAllExcludingCancelled();
    UUID add(Feature offer);
    Feature getByIdExcludingCancelled(UUID id);
    UUID update(Feature offer);
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
