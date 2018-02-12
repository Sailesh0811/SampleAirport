package coda.global.airport.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import coda.global.bean.Customer;
import coda.global.bean.Flight;
import coda.global.bean.Transaction;

public interface CustomerInterface {

	

	//List<Flight> searchFlight(HttpServletRequest request,String filter);

	List<Flight> searchWithFilter(Flight flight, String filter);

	void filterSearch(Flight flight);

	int book(Flight flight,Customer customer,String[] passenger);

	List<Transaction> viewHistory(Customer customer,String choice,int pnr);

	String cancelTicket(Customer customer,int pnr);

	String[] checkIn(Customer customer,int pnr);

}