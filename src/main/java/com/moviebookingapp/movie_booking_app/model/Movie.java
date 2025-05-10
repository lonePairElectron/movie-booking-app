package com.moviebookingapp.movie_booking_app.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Movie {

    @Id
    private String movieId;
    private String movieName;
    private String movieDescription;
}
