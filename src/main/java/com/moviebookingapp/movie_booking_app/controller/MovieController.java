package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.model.Movie;
import com.moviebookingapp.movie_booking_app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/moviebooking")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping("/addmovie")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie  movie){
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @GetMapping("/moviename")
    public ResponseEntity<List<Movie>>  getMovieByName(@RequestParam String Moviename){
        return ResponseEntity.ok(movieService.getMovieByName(Moviename));
    }


    @PutMapping("/updatemovie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id , @RequestBody Movie movie){
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    @DeleteMapping("/deletemovie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
