package com.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.Customer;
import com.train.model.ReservationForm;
import com.train.model.TrainDetails;

import com.train.repository.AdminDao;
import com.train.repository.CustomerRepository;
import com.train.repository.ReservationRepository;
import com.train.service.ReservationService;
@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private AdminDao admindao;
	@Autowired
	private CustomerRepository cd;
	
	
	@Override
	public ReservationForm addReservationForm(ReservationForm reservationForm) {
		List<Customer> cust=cd.findAll();
		for (Customer customer : cust) {
			if(customer.getUsername().equals(reservationForm.getCustomer().getUsername()))
			reservationForm.getCustomer().setcId(customer.getcId());
		}
		TrainDetails td=this.admindao.getAvailableSeat(reservationForm.getTrainDetails().getTid());
		
		  if(reservationForm.getCoachType().equals("availAcSleeperSeat"))
		    {   int Total_seat=td.getTotalAcSleeperSeat();
		        String[] coach= {"1A","2A","3A","4A","5A","6A","7A","8A","9A"};
		        int Current_total_seat_booked=this.reservationRepository.getCoachNumber(reservationForm.getTrainDetails().getTid(),"availAcSleeperSeat" );
		        String Coach_Id=coach[(Current_total_seat_booked)/50];
		        int seat_number_allocated=((Current_total_seat_booked)%50)+1;
		        reservationForm.setCoachId(Coach_Id);
		        reservationForm.setSeatNumber(seat_number_allocated);
		        int avail=td.getAvailAcSleeperSeat();
		        td.setAvailAcSleeperSeat(avail-1);
		        admindao.save(td);
		        return this.reservationRepository.save(reservationForm);
		    	
		    }
		    else if(reservationForm.getCoachType().equals("availAcSittingSeat"))
		    { 
		     int Total_seat=td.getTotalAcSittingSeat();
		     String[] coach= {"1B","2B","3B","4B","5B","6B","7B","8B","9B"};
		     int Current_total_seat_booked=this.reservationRepository.getCoachNumber(reservationForm.getTrainDetails().getTid(),"availAcSittingSeat" );
		        String Coach_Id=coach[(Current_total_seat_booked)/70];
		        int seat_number_allocated=((Current_total_seat_booked)%70)+1;
		        reservationForm.setCoachId(Coach_Id);
		        reservationForm.setSeatNumber(seat_number_allocated);
		        int avail=td.getAvailAcSittingSeat();
		        td.setAvailAcSittingSeat(avail-1);
		        admindao.save(td);
		        return this.reservationRepository.save(reservationForm);
		    	
		    	
		    }
		    else if(reservationForm.getCoachType().equals("availNonAcSleeperSeat"))
		    {   
		    	int Total_seat=td.getTotalNonAcSittingSeat();
		    	 String[] coach= {"1C","2C","3C","4C","5C","6C","7C","8C","9C"};
		    	 int Current_total_seat_booked=this.reservationRepository.getCoachNumber(reservationForm.getTrainDetails().getTid(),"availNonAcSleeperSeat" );
			        String Coach_Id=coach[(Current_total_seat_booked)/50];
			        int seat_number_allocated=((Current_total_seat_booked)%50)+1;
			        reservationForm.setCoachId(Coach_Id);
			        reservationForm.setSeatNumber(seat_number_allocated);
			        int avail=td.getAvailNonAcSleeperSeat();
			        td.setAvailNonAcSleeperSeat(avail-1);
			        admindao.save(td);
			        return this.reservationRepository.save(reservationForm);
			    	
		    	
		    				
		    }
		    else {
		    	int Total_seat=td.getTotalNonAcSittingSeat();
		    	String[] coach= {"1D","2D","3D","4D","5D"};
		    	int Current_total_seat_booked=this.reservationRepository.getCoachNumber(reservationForm.getTrainDetails().getTid(),"availNonAcSittingSeat" );
		        String Coach_Id=coach[(Current_total_seat_booked)/70];
		        int seat_number_allocated=((Current_total_seat_booked)%70)+1;
		        reservationForm.setCoachId(Coach_Id);
		        reservationForm.setSeatNumber(seat_number_allocated);
		        int avail=td.getAvailNonAcSittingSeat();
		        td.setAvailNonAcSittingSeat(avail-1);
		        admindao.save(td);
		        return this.reservationRepository.save(reservationForm);
		    	
		    }
		    
//		return this.reservationRepository.save(reservationForm);
	}
	
//	AcSleeper=50 {1A,2A,3A,4A,5A}
//	AcSitting=70 {1B.........
//	NonAcSleeper=50{1C............
//	NonAcSitting=70{1D...........

	@Override
	public ReservationForm viewReservationForm(int bookingid) {
		// TODO Auto-generated method stub
		return this.reservationRepository.findById(bookingid).get();
	}

	@Override
	public List<ReservationForm> getReservationByCustId(Long custid) {
		// TODO Auto-generated method stub
		return this.reservationRepository.getReservationbyCustId(custid);
	}
	
	  @Override
	  public boolean checkSeatAvail(String seat,int id) {
		System.out.println(seat);
	    TrainDetails td=this.admindao.getAvailableSeat(id);
	    System.out.println(td);
	    System.out.println("-----------------------------------------------------------------");
	    if(seat.equals("availNonAcSittingSeat"))
	    {
	    	System.out.println(1);
	    	if(td.getAvailNonAcSittingSeat()>0)
	    		return true;
	    	else return false;
	    				
	    }
	    else if(seat.equals("availAcSittingSeat"))
	    {
	    	System.out.println(2);
	    	if(td.getAvailAcSittingSeat()>0)
	    		return true;
	    	else return false;
	    				
	    }
	    else if(seat.equals("availNonAcSleeperSeat"))
	    {
	    	System.out.println(3);
	    	if(td.getAvailNonAcSleeperSeat()>0)
	    		return true;
	    	else return false;
	    				
	    }
	    else {
	    	System.out.println(4);
	    	if(td.getAvailAcSleeperSeat()>0)
	    		return true;
	    	else return false;
	    }
	    
	  }


}
