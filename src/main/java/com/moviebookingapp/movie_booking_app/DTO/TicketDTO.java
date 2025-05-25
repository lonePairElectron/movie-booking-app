package com.moviebookingapp.movie_booking_app.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class TicketDTO {
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
    // Getters and Setters
    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getTheatreName() {
        return theatreName;
    }
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
    public int getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
    public List<String> getSeatNumbers() {
        return seatNumbers;
    }
    public void setSeatNumbers(List<String> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

}