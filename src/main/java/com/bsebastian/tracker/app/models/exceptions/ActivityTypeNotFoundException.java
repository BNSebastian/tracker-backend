package com.bsebastian.tracker.app.models.exceptions;

public class ActivityTypeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ActivityTypeNotFoundException(String message) {
        super(message);
    }
}
