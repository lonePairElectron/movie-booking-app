package com.moviebookingapp.movie_booking_app.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Theator {

    @Id
    private String theatorId;
    private String theatorName;
    private int availableTickets =0;
    private int theatorCapacity = 100;

    private List<Show> showList;
    }