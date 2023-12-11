package com.bsebastian.tracker.app.exceptions;

public class ActivityException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ActivityException(String message) {
        super(message);
    }
}
