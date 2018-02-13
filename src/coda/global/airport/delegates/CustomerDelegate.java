package coda.global.airport.delegates;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import coda.global.airport.dao.CustomerImplementation;
import coda.global.airport.dao.CustomerLoginRegister;
import coda.global.bean.Customer;
import coda.global.bean.Flight;
import coda.global.bean.Transaction;

public class CustomerDelegate {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat timeFormat = new SimpleDateFormat("hh:mm");
	public Customer login(int id,String password) {
		CustomerLoginRegister customerLoginRegister = new CustomerLoginRegister();
		return customerLoginRegister.login(id, password);
	}
	public void register(HttpServletRequest request) {
		CustomerLoginRegister customerLoginRegister = new CustomerLoginRegister();
		customerLoginRegister.register(request);
	}
	public List<Flight> searchFlight(HttpServletRequest request, String filter) {
		Flight flight = new Flight();
		List<Flight> flightList = new LinkedList<Flight>();
		System.out.println(request.getParameter("from"));
		flight.setFrom(request.getParameter("from"));
		flight.setTo(request.getParameter("to"));
		Date date;
		try {
			date = dateFormat.parse(request.getParameter("date"));
			flight.setDate(date);

		} catch (ParseException e) {
			System.out.println("Enter a proper date ");
		}

		flight.setSeats(Integer.parseInt(request.getParameter("seats")));
		flight.setCls(request.getParameter("class"));
		System.out.println(filter);
		CustomerImplementation customer = new CustomerImplementation();
		flightList = customer.searchWithFilter(flight, filter);
		if(flightList.size()==0) {
			flightList=null;
		}
		return flightList;

		/* Searching for flight */
	}
	
	public String[] getFromCityList() {
		
		CustomerImplementation customer = new CustomerImplementation();
		return customer.cityFromList();
	}
	
	public String[] getToCityList() {
		CustomerImplementation customer = new CustomerImplementation();
		return customer.cityFromList();
	}
	
	public String addSeats(String[] seats, int pnr) {
		CustomerImplementation customerImplementation = new CustomerImplementation();
		return customerImplementation.addSeats(seats, pnr);
	}
	public String[] checkIn(Customer customer, int pnr) {
		CustomerImplementation customerImplementation = new CustomerImplementation();
		return customerImplementation.checkIn(customer, pnr);
	}
	public List<Transaction> viewHistory(Customer customer, String choice, int pnr) {
		CustomerImplementation customerImplementation = new CustomerImplementation();
		return customerImplementation.viewHistory(customer, choice, pnr);
	}
	public int book(Flight flight, Customer customer, String[] passenger) {
		CustomerImplementation customerImplementation = new CustomerImplementation();
		return customerImplementation.book(flight, customer, passenger);
	}
}
