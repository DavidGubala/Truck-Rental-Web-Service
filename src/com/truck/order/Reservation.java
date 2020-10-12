package com.truck.order;

import java.time.LocalDateTime;
import java.time.Duration;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reservation {
	private int reservationId;
	private LocalDateTime reservationDate;
	private LocalDateTime dropOffDate;
	private LocalDateTime pickUpDate;
	private Duration duration;
	
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int id) {
		this.reservationId = id;
	}
	
	public LocalDateTime getReservationDate() {
		return reservationDate;
	}
	
	public void setReservationDate(LocalDateTime date) {
		this.reservationDate = date;
	}
	
	public LocalDateTime getPickUpDate() {
		return pickUpDate;
	}
	
	public void setPickUpDate(LocalDateTime date) {
		this.pickUpDate = date;
	}
	
	public LocalDateTime getDropOffDate() {
		return dropOffDate;
	}
	
	public void setDropOffDate(LocalDateTime date) {
		this.dropOffDate = date;
	}
	
	public Duration getDuration(Duration duration) {
		return duration;
	}
	
	public void setDuration(LocalDateTime start, LocalDateTime end) {
		this.duration = Duration.between(start, end);
	}
}
