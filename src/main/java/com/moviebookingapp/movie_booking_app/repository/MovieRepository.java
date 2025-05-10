package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {

    public Optional<List<Movie>> findByMovieName(String movieName);
}
