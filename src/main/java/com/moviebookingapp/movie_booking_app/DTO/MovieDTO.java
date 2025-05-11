package com.moviebookingapp.movie_booking_app.DTO;


import lombok.Data;

@Data
public class MovieDTO {
    private String movieName;
    private String movieDescription;

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getMovieDescription() {
        return movieDescription;
    }
    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
