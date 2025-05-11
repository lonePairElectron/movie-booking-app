package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.model.Theator;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TheatorRepository extends MongoRepository<Theator,String> {

    Optional<List<Theator>> findByTheatorLocation(String theatorLocation);
}
