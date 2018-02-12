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

/**
 * Servlet implementation class AddSeatServlet
 */
@WebServlet("/Customer/AddSeatServlet")
public class AddSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSeatServlet() {
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
		Customer customer = new Customer();
		CustomerDelegate customerDelegate = new CustomerDelegate();
		customer=(Customer) session.getAttribute("customer");
		int seat = Integer.parseInt((String)session.getAttribute("seats"));
		String[] seats = new String[seat];
		int pnr = Integer.parseInt((String)session.getAttribute("pnr"));
		for(int i=0;i<seat;i++) {
			String p=""+i;
			seats[i]=request.getParameter(p);
			System.out.println(seats[i]);
		}
		RequestDispatcher rd = request.getRequestDispatcher("Customer.jsp");
		rd.include(request, response);
		PrintWriter out = response.getWriter();
		out.println(customerDelegate.addSeats(seats, pnr));
	}

}
