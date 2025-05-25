package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.entity.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByMovieName(String movieName);
}