package com.moviebookingapp.movie_booking_app.controller;

import com.moviebookingapp.movie_booking_app.DTO.ShowDTO;
import com.moviebookingapp.movie_booking_app.model.Movie;
import com.moviebookingapp.movie_booking_app.model.Show;
import com.moviebookingapp.movie_booking_app.model.Theator;
import com.moviebookingapp.movie_booking_app.repository.MovieRepository;
import com.moviebookingapp.movie_booking_app.repository.ShowRepository;
import com.moviebookingapp.movie_booking_app.repository.TheatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatorRepository theaterRepository;

    @PostMapping("/add")
    public ResponseEntity<ShowDTO> addShow(@RequestBody Show show) {
        ShowDTO showDTO = new ShowDTO();
        Movie movieId = movieRepository.findById(show.getMovieId()).orElseThrow(() -> new RuntimeException("Movie not found"));
        Theator theaterId = theaterRepository.findById(show.getTheaterId()).orElseThrow(() -> new RuntimeException("Theater not found"));
        if (movieId == null || theaterId == null) {
            return ResponseEntity.badRequest().build();
        }
        showDTO.setMovie(movieId);
        showDTO.setTheator(theaterId);
        showDTO.setShowTime(show.getShowTime());
        showRepository.save(showDTO);
        return ResponseEntity.ok(showDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ShowDTO>> getAllShows() {
        List<ShowDTO> shows = showRepository.findAll();
        return ResponseEntity.ok(shows);
    }
}
