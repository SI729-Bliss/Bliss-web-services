package com.beautyservices.bliss.bookingmanagement.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("User with ID " + userId + " was not found.");
    }
}
