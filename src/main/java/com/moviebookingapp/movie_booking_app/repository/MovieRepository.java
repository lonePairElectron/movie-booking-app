package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, Integer> {

}
