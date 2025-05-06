package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.model.Movie;
import com.moviebookingapp.movie_booking_app.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){

        return movieRepository.findAll();
    }
}
