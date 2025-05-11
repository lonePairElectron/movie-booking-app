package com.moviebookingapp.movie_booking_app.DTO;

import com.moviebookingapp.movie_booking_app.model.Movie;
import com.moviebookingapp.movie_booking_app.model.Theator;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ShowDTO {

    private Movie movie;
    private Theator theator;
    private LocalDateTime showTime;

    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public Theator getTheator() {
        return theator;
    }
    public void setTheator(Theator theator) {
        this.theator = theator;
    }
    public LocalDateTime getShowTime() {
        return showTime;
    }
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
