package com.bsebastian.tracker.app.models.exceptions;

public class ActivityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ActivityNotFoundException(String message) {
        super(message);
    }
}
