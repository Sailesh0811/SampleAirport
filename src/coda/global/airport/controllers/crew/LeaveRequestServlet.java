package coda.global.airport.controllers.crew;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.airport.dao.CrewImplementation;
import coda.global.airport.delegates.CrewDelegate;
import coda.global.bean.Crew;

/**
 * Servlet implementation class LeaveRequestServlet
 */
@WebServlet("/Crew/LeaveRequestServlet")
public class LeaveRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("crewId") != null) {
			System.out.println("crew  id" + (String) session.getAttribute("crewId"));
			System.out.println("dfndljfd");

			RequestDispatcher rd = request.getRequestDispatcher("leaveRequest.jsp");
			rd.include(request, response);
		} else {
			System.out.println("dfldnfjlnjs");
			session.setAttribute("url", "LeaveRequestServlet");
			response.sendRedirect("CrewLogin.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CrewDelegate crewDelegate = new CrewDelegate();
		Crew crew=(Crew)session.getAttribute("crew");
		System.out.println("crew  id" + (String) session.getAttribute("crewId"));
		System.out.println("dfndljfd");
		int noOfDays = Integer.parseInt(request.getParameter("noOfDays"));
		String date = request.getParameter("date");
		session.setAttribute("date", date);
		session.setAttribute("noOfDays", noOfDays);
		request.setAttribute("no", noOfDays);
		
		request.setAttribute("date", date);
		
		if (request.getParameter("leaveType").equals("COMP")) {
			request.setAttribute("type", 0);
		} else if (request.getParameter("leaveType").equals("NON")) {
			String result=crewDelegate.insertLeave(crew, date, noOfDays);
			PrintWriter out = response.getWriter();
			RequestDispatcher rd = request.getRequestDispatcher("Crew.jsp");
			rd.include(request, response);
			out.print(result);
			out.println("");
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("leaveRequest.jsp");
		rd.include(request, response);

	}

}
