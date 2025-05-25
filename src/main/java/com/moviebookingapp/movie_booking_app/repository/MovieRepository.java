package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByName(String name);
    Movie findByNameAndTheatreName(String name, String theatreName);
}