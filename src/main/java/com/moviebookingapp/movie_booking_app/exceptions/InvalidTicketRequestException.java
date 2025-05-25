package com.moviebookingapp.movie_booking_app.exceptions;

public class InvalidTicketRequestException extends RuntimeException {
    public InvalidTicketRequestException(String message) {
        super(message);
    }
}