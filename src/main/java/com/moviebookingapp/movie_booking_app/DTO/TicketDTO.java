package com.moviebookingapp.movie_booking_app.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TicketDTO {
    // Getters and Setters
    @NotBlank(message = "Movie name cannot be blank")
    private String movieName;

    @NotBlank(message = "Theatre name cannot be blank")
    private String theatreName;

    @Min(value = 1, message = "At least one ticket must be booked")
    private int numberOfTickets;

    private List<String> seatNumbers;

    public TicketDTO(String movieName, String theatreName, int numberOfTickets, List<String> seatNumbers) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.numberOfTickets = numberOfTickets;
        this.seatNumbers = seatNumbers;
    }

}