package Sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.airport.AdminImplementation;
import coda.global.airport.CrewRegistrationLogin;
import coda.global.bean.Crew;
import coda.global.bean.Flight;

/**
 * Servlet implementation class AddFlightServlet
 */
@WebServlet("/AddFlightServlet")
public class AddFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlightServlet() {
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
		System.out.println("dafdffadf");
		HttpSession session = request.getSession();
		if ((((Integer)session.getAttribute("adminId"))==1)) {
			Flight flight = new Flight();
			flight.setAirline(request.getParameter("name"));
			flight.setFlightNo(request.getParameter("flightNo"));
			flight.setFrom(request.getParameter("origin"));
			flight.setTo(request.getParameter("destination"));
			AdminImplementation ai = new AdminImplementation();
			String result = ai.addFlight(flight);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<h1>" + result + "</h1>");
		} else {
			session.setAttribute("url", "addFlight.jsp");
			response.sendRedirect("AdminLogin.jsp");
		}
	}

}
