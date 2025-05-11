package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.DTO.ShowDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<ShowDTO, String> {
    // Custom query methods can be defined here if needed
}
