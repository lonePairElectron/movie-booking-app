package com.moviebookingapp.movie_booking_app.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Theator {

    @Id
    private String theatorId;
    private String theatorName;
    private Integer theatorCapacity;
    private String theatorLocation;
    }