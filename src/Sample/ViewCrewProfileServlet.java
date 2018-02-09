package Sample;

import java.io.IOException;

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
 * Servlet implementation class ViewCrewProfileServlet
 */
@WebServlet("/ViewCrewProfileServlet")
public class ViewCrewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCrewProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("fanfjkafnjkdfndakfjnkjfnak");
		HttpSession session = request.getSession();		
		System.out.println("crew  id"+(String)session.getAttribute("crewId"));
		    if(session.getAttribute("crewId")!=null) {				
				System.out.println("crew  id"+(String)session.getAttribute("crewId"));
				System.out.println("dfndljfd");
				//response.sendRedirect("book.jsp");
				CrewImplementation cr = new CrewImplementation();
				Crew crew = (Crew) session.getAttribute("crew");
				String res = cr.viewProfile(crew);
				request.setAttribute("id", crew.getCrewId());
				request.setAttribute("name", crew.getName());
				request.setAttribute("designation", crew.getDesignation());
				request.setAttribute("leavedays", crew.getLeaveDays());
				request.setAttribute("contactno", crew.getContactNo());
				System.out.println(res);
				RequestDispatcher rd = request.getRequestDispatcher("viewProfile.jsp");
				rd.include(request, response);
			}
			else {
				System.out.println("dfldnfjlnjs");
				session.setAttribute("url", "ViewCrewProfileServlet");
				response.sendRedirect("CrewLogin.jsp");
				
			}
		    
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("viewProfile.jsp");
		HttpSession session = request.getSession();
		CrewImplementation cr = new CrewImplementation();
		Crew crew = (Crew) session.getAttribute("crew");
		String res = cr.viewProfile(crew);
		rd.include(request, response);
		
	}

}
