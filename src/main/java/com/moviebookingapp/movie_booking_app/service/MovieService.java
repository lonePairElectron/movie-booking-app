package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.DTO.MovieDTO;
import com.moviebookingapp.movie_booking_app.DTO.TicketDTO;
import com.moviebookingapp.movie_booking_app.entity.Movie;
import com.moviebookingapp.movie_booking_app.entity.Ticket;
import com.moviebookingapp.movie_booking_app.exceptions.DuplicateEntryException;
import com.moviebookingapp.movie_booking_app.exceptions.MovieNotFoundException;
import com.moviebookingapp.movie_booking_app.exceptions.ResourceNotFoundException;
import com.moviebookingapp.movie_booking_app.repository.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketService ticketService;

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found in the database.");
        }
        return movies;
    }

    public Movie getMovieByName(String name) {
        return movieRepository.findByName(name)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + name));
    }

    public Movie addMovie(MovieDTO movieDTO) {
        if (movieRepository.findByNameAndTheatreName(movieDTO.getName(), movieDTO.getTheatreName()) != null) {
            throw new DuplicateEntryException("Movie already exists in this theatre: " + movieDTO.getName());
        }
        Movie movie = new Movie(movieDTO.getName(), movieDTO.getTheatreName(), movieDTO.getTotalTickets());
        return movieRepository.save(movie);
    }

    public Movie updateTickets(String name, String theatreName, int bookedTickets) {
        Movie movie = movieRepository.findByNameAndTheatreName(name, theatreName);
        if (movie == null) {
            throw new ResourceNotFoundException("Movie not found: " + name + " at theatre " + theatreName);
        }
        if (movie.getAvailableTickets() < bookedTickets) {
            throw new IllegalArgumentException("Not enough tickets available");
        }
        movie.setAvailableTickets(movie.getAvailableTickets() - bookedTickets);
        movie.setStatus(movie.getAvailableTickets() == 0 ? "SOLD OUT" : "BOOK ASAP");
        return movieRepository.save(movie);
    }
    @PostMapping("/tickets")
    public Ticket bookTickets(@Valid @RequestBody TicketDTO ticketDTO) {
        return ticketService.bookTickets(ticketDTO);
    }


    public String deleteMovie(String id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
        return "Movie deleted successfully.";
    }

}