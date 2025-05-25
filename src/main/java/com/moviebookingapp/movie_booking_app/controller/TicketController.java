package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.DTO.TicketDTO;
import com.moviebookingapp.movie_booking_app.entity.Ticket;
import com.moviebookingapp.movie_booking_app.exceptions.InvalidTicketRequestException;
import com.moviebookingapp.movie_booking_app.exceptions.MovieNotFoundException;
import com.moviebookingapp.movie_booking_app.exceptions.TicketBookingException;
import com.moviebookingapp.movie_booking_app.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class TicketController {

    @Autowired
    private TicketService ticketService;

   @PreAuthorize("hasRole('USER')")
   @PostMapping("/tickets")
   public ResponseEntity<?> bookTickets(@Valid @RequestBody TicketDTO ticketDTO) {
       try {
           Ticket bookedTicket = ticketService.bookTickets(ticketDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(bookedTicket);
       } catch (MovieNotFoundException ex) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + ex.getMessage() + "\" }");
       } catch (TicketBookingException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + ex.getMessage() + "\" }");
       } catch (InvalidTicketRequestException ex) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"error\": \"" + ex.getMessage() + "\" }");
       } catch (Exception ex) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"error\": \"An unexpected error occurred\" }");
       }
   }

  // @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/tickets/{movieName}")
  public ResponseEntity<?> getBookedTickets(@PathVariable String movieName) {
      try {
          List<Ticket> bookedTickets = ticketService.getBookedTickets(movieName);
          return ResponseEntity.status(HttpStatus.OK).body(bookedTickets);
      } catch (MovieNotFoundException ex) {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{ \"error\": \"" + ex.getMessage() + "\" }");
      } catch (Exception ex) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{ \"error\": \"An unexpected error occurred\" }");
      }
  }
}