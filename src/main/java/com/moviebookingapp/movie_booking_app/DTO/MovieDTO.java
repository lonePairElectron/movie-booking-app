package com.moviebookingapp.movie_booking_app.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MovieDTO {
    // Getters and Setters
    @NotBlank(message = "Movie name cannot be blank")
    private String name;

    @NotBlank(message = "Theatre name cannot be blank")
    private String theatreName;

    @Min(value = 1, message = "Total tickets should be at least 1")
    private int totalTickets;

    public MovieDTO(String name, String theatreName, int totalTickets) {
        this.name = name;
        this.theatreName = theatreName;
        this.totalTickets = totalTickets;
    }


}