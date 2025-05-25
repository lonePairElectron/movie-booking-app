package com.moviebookingapp.movie_booking_app.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MovieDTO {
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
    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTheatreName() {
        return theatreName;
    }
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
    public int getTotalTickets() {
        return totalTickets;
    }
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }


}