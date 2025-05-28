package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {

    Optional<Users> findByUsername(String username);
    Users findByEmail(String email);
}
