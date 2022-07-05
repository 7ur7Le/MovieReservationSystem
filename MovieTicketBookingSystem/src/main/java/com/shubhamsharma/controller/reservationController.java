package com.shubhamsharma.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shubhamsharma.model.Reservation;
import com.shubhamsharma.repository.MovieRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class reservationController {
  @Autowired
  MovieRepository movieRepository;
  @GetMapping("/reservations")
  public ResponseEntity<List<Reservation>> getAllReservations(@RequestParam(required = false) String title) {
    try {
      List<Reservation> reservation = new ArrayList<Reservation>();
      if (title == null)
        movieRepository.findAll().forEach(reservation::add);
      else
        movieRepository.findByMovieTitleContaining(title).forEach(reservation::add);
      if (reservation.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(reservation, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Reservation> getTutorialById(@PathVariable("id") long id) {
    Optional<Reservation> movieData = movieRepository.findById(id);
    if (movieData.isPresent()) {
      return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/reservations")
  public ResponseEntity<Reservation> createTutorial(@RequestBody Reservation res) {
    try {
    	Reservation reservation = movieRepository
          .save(new Reservation(res.getMovieTitle(), res.getSeat(), true));
      return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/reservations/{id}")
  public ResponseEntity<Reservation> updateReservation(@PathVariable("id") long id, @RequestBody Reservation res) {
    Optional<Reservation> tutorialData = movieRepository.findById(id);
    if (tutorialData.isPresent()) {
    	Reservation _reservation = tutorialData.get();
    	_reservation.setTitle(res.getMovieTitle());
    	_reservation.setSeat(res.getSeat());
    	_reservation.setReserved(res.isReserved());
      return new ResponseEntity<>(movieRepository.save(_reservation), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/reservations/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      movieRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
