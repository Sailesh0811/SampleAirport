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
 * Servlet implementation class ViewAssignedDutyServlet
 */
@WebServlet("/ViewAssignedDutyServlet")
public class ViewAssignedDutyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAssignedDutyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		 if(session.getAttribute("crewId")!=null) {				
				System.out.println("crew  id"+(String)session.getAttribute("crewId"));
				System.out.println("dfndljfd");
				Crew crew=(Crew)session.getAttribute("crew");
				CrewImplementation cr = new CrewImplementation();
				String[] result =cr.assignedDuty(crew);
				String[] assigned=result[0].split(",");
				String[] crewAssigned = result[1].split(",");
				request.setAttribute("some", assigned);
				request.setAttribute("s", crewAssigned);
				RequestDispatcher rd = request.getRequestDispatcher("viewAssignedDuty.jsp");
				rd.include(request, response);
			}
			else {
				System.out.println("dfldnfjlnjs");
				session.setAttribute("url", "ViewAssignedDutyServlet");
				response.sendRedirect("CrewLogin.jsp");
				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
