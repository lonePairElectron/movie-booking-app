package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.DTO.BookingTicketDTO;
import com.moviebookingapp.movie_booking_app.model.BookTicket;
import com.moviebookingapp.movie_booking_app.service.BookTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookticket")
public class BookTicketController {
    @Autowired
    private BookTicketService bookTicketService;
    @PostMapping("createbooking")
    public ResponseEntity<BookingTicketDTO> createBooking(@RequestBody BookTicket bookTicket) {
        return ResponseEntity.ok(bookTicketService.createBooking(bookTicket));
    }

}
