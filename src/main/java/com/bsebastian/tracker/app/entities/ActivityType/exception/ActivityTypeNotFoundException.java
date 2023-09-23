package com.bsebastian.tracker.app.entities.ActivityType.exception;

public class ActivityTypeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ActivityTypeNotFoundException(String message) {
        super(message);
    }
}
