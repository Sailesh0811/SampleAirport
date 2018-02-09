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
import coda.global.bean.Flight;

/**
 * Servlet implementation class ScheduleFlightServlet
 */
@WebServlet("/ScheduleFlightServlet")
public class ScheduleFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleFlightServlet() {
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
			AdminImplementation ai = new AdminImplementation();
			Flight flight = new Flight();
			flight.setId(Integer.parseInt(request.getParameter("flightId")));
			String atime=request.getParameter("atime");
			String dtime=request.getParameter("dtime");
			String dat= request.getParameter("date");
			flight.setBusinessFare(Float.parseFloat(request.getParameter("bFare")));
			flight.setEconomyFare(Float.parseFloat(request.getParameter("bFare")));
			flight.setEconomySeats(Integer.parseInt(request.getParameter("eNo")));
			flight.setBusinessSeats(Integer.parseInt(request.getParameter("bNo")));
			String result=ai.scheduleFlight(flight, atime, dtime, dat);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<h1>" + result + "</h1>");
		} else {
			session.setAttribute("url", "addFlightSeat.jsp");
			response.sendRedirect("AdminLogin.jsp");
		}
	}

}
