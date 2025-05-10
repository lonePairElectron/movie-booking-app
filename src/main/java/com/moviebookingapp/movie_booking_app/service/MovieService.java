package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.model.Movie;
import com.moviebookingapp.movie_booking_app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){

        return movieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        return  movieRepository.save(movie);
    }

    public List<Movie> getMovieByName(String movieName) {

        Optional<List<Movie>> moviesName = movieRepository.findByMovieName(movieName);
        if (moviesName.isPresent()) {
            return moviesName.get();
        } else throw new RuntimeException("Movie not found");
    }
//    public Movie updateMovie(String id, Movie movie) {
//        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
//        existingMovie.setName(movie.getName());
//        existingMovie.setDescription(movie.getDescription());
//        return movieRepository.save(existingMovie);
//    }
    public void deleteMovie(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.delete(movie);
    }
}
