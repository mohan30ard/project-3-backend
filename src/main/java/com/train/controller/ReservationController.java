package com.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.model.Customer;
import com.train.model.ReservationForm;
import com.train.service.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("/reserve")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/add")
	public ReservationForm addReservationForm(@RequestBody ReservationForm reservationForm) {
		System.out.println("----------------------------------------");
		System.out.println(reservationForm);
		System.out.println("********************************************");
		return this.reservationService.addReservationForm(reservationForm);
	}
	
	@GetMapping("/view/{bookingId}")
	public ReservationForm viewReservationForm(@PathVariable("bookingId") int bookingId) {
		return this.reservationService.viewReservationForm(bookingId);
	} 

	@GetMapping("/cust/{custId}")
	public List<ReservationForm> viewReservationFormByCustId(@PathVariable("custId") Long custId) {
		return this.reservationService.getReservationByCustId(custId);
	}
	



	@GetMapping("/checkseats/{seat}/{id}")
	  public boolean checkSeatAvail(@PathVariable String seat,@PathVariable int id) {
	    return this.reservationService.checkSeatAvail(seat,id);
	  }

}
