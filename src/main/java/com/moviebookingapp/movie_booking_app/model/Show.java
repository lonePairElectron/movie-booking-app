package com.moviebookingapp.movie_booking_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Show {
    @Id
    private String showId;
    private String movieId;
    private String theaterId;
    private LocalDateTime showTime;

    public String getShowId() {
        return showId;
    }
    public void setShowId(String showId) {
        this.showId = showId;
    }
    public String getMovieId() {
        return movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public String getTheaterId() {
        return theaterId;
    }
    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }
    public LocalDateTime getShowTime() {
        return showTime;
    }
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}