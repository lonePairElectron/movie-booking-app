package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.DTO.MovieDTO;
import com.moviebookingapp.movie_booking_app.entity.Movie;
import com.moviebookingapp.movie_booking_app.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/search/{moviename}")
    public Movie searchMovie(@PathVariable String moviename) {
        return movieService.getMovieByName(moviename);
    }

    @PostMapping("/add")
    public Movie addMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.addMovie(movieDTO);
    }

    @PutMapping("/{moviename}/{theatreName}/update/{ticket}")
    public Movie updateTicketStatus(@PathVariable String moviename, @PathVariable String theatreName, @PathVariable int ticket) {
        return movieService.updateTickets(moviename, theatreName, ticket);
    }

    @DeleteMapping("/{moviename}/delete/{id}")
    public String deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }
}