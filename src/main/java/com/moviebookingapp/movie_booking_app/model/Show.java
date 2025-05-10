package com.moviebookingapp.movie_booking_app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Show {
    @Id
    private String showId;
    private String theatorId;
    private LocalDateTime showTime;
}