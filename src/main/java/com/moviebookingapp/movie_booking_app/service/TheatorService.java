package com.moviebookingapp.movie_booking_app.service;

import com.moviebookingapp.movie_booking_app.model.Theator;
import com.moviebookingapp.movie_booking_app.repository.TheatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatorService {

    @Autowired
    public TheatorRepository theatorRepository;

    public Theator addTheator(Theator theator) {
        return theatorRepository.save(theator);
    }

    public Theator updateTheator(Theator theator) {
        return theatorRepository.save(theator);
    }
    public void deleteTheator(String id) {
        theatorRepository.deleteById(id);
    }
    public List<Theator> getTheatorByLocation(String location) {
        Optional<List<Theator>> listOfTheator = theatorRepository.findByTheatorLocation(location);
        if(listOfTheator.isPresent()){
            return listOfTheator.get();
        }
        else throw new RuntimeException("No Theator Found");
    }
}
