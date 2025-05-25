package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.DTO.TicketDTO;
import com.moviebookingapp.movie_booking_app.entity.Movie;
import com.moviebookingapp.movie_booking_app.entity.Ticket;
import com.moviebookingapp.movie_booking_app.exceptions.InvalidTicketRequestException;
import com.moviebookingapp.movie_booking_app.exceptions.MovieNotFoundException;
import com.moviebookingapp.movie_booking_app.exceptions.TicketBookingException;
import com.moviebookingapp.movie_booking_app.repository.MovieRepository;
import com.moviebookingapp.movie_booking_app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Ticket bookTickets(TicketDTO ticketDTO) {
        Movie movie = movieRepository.findByNameAndTheatreName(ticketDTO.getMovieName(), ticketDTO.getTheatreName());

        // Movie not found
        if (movie == null) {
            throw new MovieNotFoundException("Movie not found: " + ticketDTO.getMovieName());
        }

        // Invalid ticket request
        if (ticketDTO.getNumberOfTickets() <= 0) {
            throw new InvalidTicketRequestException("Number of tickets must be greater than zero.");
        }

        // Not enough tickets available
        if (movie.getAvailableTickets() < ticketDTO.getNumberOfTickets()) {
            throw new TicketBookingException("Not enough tickets available.");
        }

        // Book tickets and update availability
        Ticket ticket = new Ticket(ticketDTO.getMovieName(), ticketDTO.getTheatreName(), ticketDTO.getNumberOfTickets(), ticketDTO.getSeatNumbers());
        movie.setAvailableTickets(movie.getAvailableTickets() - ticketDTO.getNumberOfTickets());
        movieRepository.save(movie); // Update ticket availability
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getBookedTickets(String movieName) {
        List<Ticket> tickets = ticketRepository.findByMovieName(movieName);
        if (tickets.isEmpty()) {
            throw new MovieNotFoundException("Movie not found: " + movieName);
        }
        return tickets;
    }
}