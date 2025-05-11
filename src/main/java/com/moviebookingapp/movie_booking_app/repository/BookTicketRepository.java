package com.moviebookingapp.movie_booking_app.repository;

import com.moviebookingapp.movie_booking_app.DTO.BookingTicketDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookTicketRepository extends MongoRepository<BookingTicketDTO, String> {

}
