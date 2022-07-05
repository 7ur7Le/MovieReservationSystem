package com.shubhamsharma.model;

import javax.persistence.*;
@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "MovieTitle")
	private String movieTitle;
	@Column(name = "seat")
	private String seat;
	@Column(name = "Reserved")
	private boolean reserved;
	public Reservation() {
	}
	public Reservation(String title, String seat, boolean reserved) {
		this.movieTitle = title;
		this.seat = seat;
		this.reserved = reserved;
	}
	public long getId() {
		return id;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setTitle(String title) {
		this.movieTitle = title;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String description) {
		this.seat = description;
	}
	public boolean isReserved() {
		return reserved;
	}
	public void setReserved(boolean isReserved) {
		this.reserved = isReserved;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", Movie title=" + movieTitle + ", Seat =" + seat + ", reserved =" + reserved + "]";
	}
}

