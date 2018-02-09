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

import coda.global.airport.CrewImplementation;
import coda.global.bean.Crew;

/**
 * Servlet implementation class CompensationLeaveServlet
 */
@WebServlet("/CompensationLeaveServlet")
public class CompensationLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompensationLeaveServlet() {
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
		Crew crew= (Crew) session.getAttribute("crew");
		CrewImplementation cr = new CrewImplementation();
		System.out.println("che"+(Integer)session.getAttribute("noOfDays"));
		int noOfDays = (Integer)session.getAttribute("noOfDays");
		System.out.println("days"+noOfDays);
		String[] cd = new String[noOfDays];
		for(int i=0;i<noOfDays;i++) {
			String p="some"+(i+1);
			System.out.println(p);
			cd[i]=request.getParameter(p);
			System.out.println("cd"+cd[i]);
		}
		String date = (String)session.getAttribute("date");
		String result = cr.leaveRequest(crew,date,cd,noOfDays);
		System.out.println(result);
		RequestDispatcher rd = request.getRequestDispatcher("Crew.jsp");
		rd.include(request, response);
		PrintWriter out = response.getWriter();
		out.println("<h2>"+result+"</h2>");
	}

}
