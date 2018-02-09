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
 * Servlet implementation class SlotRequestServlet
 */
@WebServlet("/SlotRequestServlet")
public class SlotRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();		
		 if(session.getAttribute("crewId")!=null) {				
				System.out.println("crew  id"+(String)session.getAttribute("crewId"));				
				RequestDispatcher rd = request.getRequestDispatcher("slotRequest.jsp");
				rd.include(request, response);
			}
			else {				
				session.setAttribute("url", "SlotRequestServlet");
				response.sendRedirect("CrewLogin.jsp");				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		System.out.println("crew  id"+(String)session.getAttribute("crewId"));
		if(request.getParameter("date")!=null) {
			request.setAttribute("check", true);
		}
		String d = request.getParameter("date");
		CrewImplementation cr = new CrewImplementation();
		Crew crew = new Crew();
		String result=cr.slotRequest(crew, d,request.getParameter("destination") );
		System.out.println(result);
		String[] some = result.split(",");
		request.setAttribute("some", some);
		RequestDispatcher rd = request.getRequestDispatcher("slotRequest.jsp");
		rd.include(request, response);
	}

}
