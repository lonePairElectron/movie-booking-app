package com.moviebookingapp.movie_booking_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/moviebooking")
public class MovieController {


    @GetMapping("/all")
    public String getAllMovies(){
        return "hello world";
    }

}
