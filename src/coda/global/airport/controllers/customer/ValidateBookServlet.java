package coda.global.airport.controllers.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.airport.dao.CustomerImplementation;
import coda.global.airport.delegates.CustomerDelegate;
import coda.global.bean.Customer;
import coda.global.bean.Flight;

/**
 * Servlet implementation class ValidateBookServlet
 */
@WebServlet("/Customer/ValidateBookServlet")
public class ValidateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("book");
		HttpSession session = request.getSession();
		int seats=Integer.parseInt((String)session.getAttribute("seats"));
		System.out.println(seats);
		String[] passengerDetails = new String[seats];
    	for(int i=0;i<seats;i++) {
    		System.out.println("i"+i);
    		String p="passenger"+(i+1);
    		passengerDetails[i]=(String)request.getParameter(p);
    		System.out.println(p+passengerDetails[i]);
    	}
		Flight flight = (Flight) session.getAttribute("flight");
		Customer customer = (Customer) session.getAttribute("customer");
		System.out.println("customer id in booking"+customer.getCustomerId());
		System.out.println("Avil no"+flight.getAvailableFlightNo());
		CustomerDelegate cust = new CustomerDelegate();
		int pnrNo=cust.book(flight,customer,passengerDetails);
		session.setAttribute("pnr", pnrNo);
		response.sendRedirect("IndexServlet");
	}

}
