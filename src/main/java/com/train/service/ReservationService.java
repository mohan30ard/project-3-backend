package com.train.service;

import java.util.List;

import com.train.model.ReservationForm;



public interface ReservationService {
	
	public ReservationForm addReservationForm(ReservationForm reservationForm);
	
	public ReservationForm viewReservationForm(int bookingid);
	
	public List<ReservationForm> getReservationByCustId(Long custid);


	public boolean checkSeatAvail(String seat,int id);


}
