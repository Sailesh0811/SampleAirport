package coda.global.airport.controllers.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.bean.Flight;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/Customer/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
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
		HttpSession session = request.getSession();	
		PrintWriter out = response.getWriter();
		Flight flight=new Flight();
		System.out.println("seats"+session.getAttribute("seats"));
		request.setAttribute("seats", session.getAttribute("seats"));
		System.out.println(session.getAttribute("seats"));		
		flight.setAvailableFlightNo(Integer.parseInt(request.getParameter("scheduleFlightNo")));
		flight.setSeats(Integer.parseInt((String)session.getAttribute("seats")));
		flight.setCls((String)session.getAttribute("cls"));
		try {
			flight.setDate(new SimpleDateFormat("yyyy-MM-dd").parse((String)session.getAttribute("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("flight", flight);			
		if(session.getAttribute("customerId")!=null) {
			
			System.out.println("Customer id"+(String)session.getAttribute("customerId"));
			response.sendRedirect("book.jsp");			
		}
		else {
			session.setAttribute("url", "book.jsp");
			response.sendRedirect("login.jsp");
		}
		
	}

}
