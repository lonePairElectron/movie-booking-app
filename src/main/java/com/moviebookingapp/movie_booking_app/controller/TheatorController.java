package com.moviebookingapp.movie_booking_app.controller;


import com.moviebookingapp.movie_booking_app.model.Theator;
import com.moviebookingapp.movie_booking_app.service.TheatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theator")
public class TheatorController {

    @Autowired
    private TheatorService theatorService;

    @PostMapping("/addtheator")
    public ResponseEntity<Theator> addTheator(@RequestBody Theator theator) {
        return ResponseEntity.ok(theatorService.addTheator(theator));
    }

    @GetMapping("/getalltheatorsbylocation")
    public ResponseEntity<List<Theator>> getTheatorByLocation(@RequestParam String location) {
        return ResponseEntity.ok(theatorService.getTheatorByLocation(location));
    }

    @PutMapping("/updatetheator/{id}")
    public ResponseEntity<Theator> updateTheator(@PathVariable String id,@RequestBody Theator theator) {
        return ResponseEntity.ok(theatorService.updateTheator(theator));
    }

    @DeleteMapping("/deletetheator/{id}")
    public ResponseEntity<Void> deleteTheator(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        theatorService.deleteTheator(id);

        return ResponseEntity.ok().build();
    }
}
