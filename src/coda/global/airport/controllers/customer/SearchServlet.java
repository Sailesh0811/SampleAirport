package coda.global.airport.controllers.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.airport.dao.CustomerImplementation;
import coda.global.airport.delegates.CustomerDelegate;
import coda.global.bean.Flight;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/Customer/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDelegate customer = new CustomerDelegate();
		String from[]=customer.getFromCityList();
		String to[]=customer.getToCityList();
		request.setAttribute("from", from);
		request.setAttribute("to", to);
		RequestDispatcher dispatch = request.getRequestDispatcher("SearchFlight.jsp");
		dispatch.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerDelegate customer = new CustomerDelegate();
		HttpSession session = request.getSession(true);
		List<Flight> flightList = new LinkedList<Flight>();
		session.setAttribute("cls", request.getParameter("class"));
		session.setAttribute("seats", request.getParameter("seats"));
		session.setAttribute("date", request.getParameter("date"));
		System.out.println("seats in search"+session.getAttribute("seats"));
		flightList = customer.searchFlight(request,request.getParameter("filter"));		
		if(flightList==null) {
			System.out.println("fadf");
			request.setAttribute("f", 1);
			request.setAttribute("flight", flightList);
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchFlight.jsp");
			dispatch.include(request, response);
		}else {			
			RequestDispatcher dispatch = request.getRequestDispatcher("SearchFlight.jsp");
		System.out.println("daddfdfdfd");
		request.setAttribute("f", 0);
		request.setAttribute("flight", flightList);
		dispatch.include(request, response);
		}
		
		
		
		
	}

}
