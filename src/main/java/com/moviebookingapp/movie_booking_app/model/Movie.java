package com.moviebookingapp.movie_booking_app.model;

import org.springframework.data.annotation.Id;

public class Movie {


    @Id
    private int movieId;
    private String movieName;
    private String movieDescription;


}
