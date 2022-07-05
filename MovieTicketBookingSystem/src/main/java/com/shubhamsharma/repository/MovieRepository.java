package com.shubhamsharma.repository;

//MovieRepository

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.shubhamsharma.model.Reservation;
public interface MovieRepository extends JpaRepository<Reservation, Long> {
  List<Reservation> findByReserved(boolean reserved);
  List<Reservation> findByMovieTitleContaining(String movieTitle);
}