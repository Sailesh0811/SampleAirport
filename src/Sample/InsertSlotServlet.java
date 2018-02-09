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
 * Servlet implementation class InsertSlotServlet
 */
@WebServlet("/InsertSlotServlet")
public class InsertSlotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSlotServlet() {
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
		System.out.println(request.getParameter("flightNo"));
		HttpSession session = request.getSession();
		Crew crew = (Crew) session.getAttribute("crew");
		CrewImplementation cr = new CrewImplementation();
		String result=cr.insertSlot(crew, Integer.parseInt(request.getParameter("flightNo")));
		
		RequestDispatcher rd = request.getRequestDispatcher("Crew.jsp");
		rd.include(request, response);
		PrintWriter out = response.getWriter();
		out.println("<h2>"+result+"</h2>");
	}

}
