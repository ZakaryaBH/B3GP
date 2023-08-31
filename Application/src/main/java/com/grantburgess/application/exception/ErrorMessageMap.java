package com.grantburgess.application.exception;

import com.grantburgess.ports.database.TaskGateway;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageMap {
    private ErrorMessageMap() {}

    public static Map<Class, String> errors = new HashMap<>();

    static {
        errors.put(TaskGateway.OfferNotFoundException.class, "Offer not found");
        errors.put(TaskGateway.CannotCancelOfferThatHasExpiredException.class, "Cannot cancel expired offer");
        errors.put(TaskGateway.OfferEndDateCannotBeBeforeCurrentSystemDateException.class, "Offer end date must be earlier than the current date");
        errors.put(TaskGateway.OfferNameAlreadyExistsException.class, "Offer name already exists");
        errors.put(TaskGateway.OfferStartDateGreaterThanEndDateException.class, "Offer start date must be less than end date");
    }
}
