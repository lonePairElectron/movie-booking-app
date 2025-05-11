package com.moviebookingapp.movie_booking_app.DTO;

import com.moviebookingapp.movie_booking_app.model.Show;

import java.util.List;

public class BookingTicketDTO {

    private String id;
    private Integer numberOfTickets;
    private List<String> seatNumbers;
    private Double totalPrice;
    private Show show;

}
