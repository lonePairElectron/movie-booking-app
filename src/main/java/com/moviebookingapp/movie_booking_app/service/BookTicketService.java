package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.DTO.BookingTicketDTO;
import com.moviebookingapp.movie_booking_app.DTO.ShowDTO;
import com.moviebookingapp.movie_booking_app.model.BookTicket;
import com.moviebookingapp.movie_booking_app.model.Show;
import com.moviebookingapp.movie_booking_app.model.Theator;
import com.moviebookingapp.movie_booking_app.repository.ShowRepository;
import com.moviebookingapp.movie_booking_app.repository.TheatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTicketService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TheatorRepository TheatorRepository;

    public BookingTicketDTO createBooking(BookTicket bookingTicket) {
return null;
    }
}
