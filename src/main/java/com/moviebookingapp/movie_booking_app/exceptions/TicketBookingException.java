package com.moviebookingapp.movie_booking_app.exceptions;

public class TicketBookingException extends RuntimeException {
    public TicketBookingException(String message) {
        super(message);
    }
}