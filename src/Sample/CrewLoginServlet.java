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

import coda.global.airport.CrewRegistrationLogin;
import coda.global.airport.CustomerLoginRegister;
import coda.global.bean.Crew;
import coda.global.bean.Customer;

/**
 * Servlet implementation class CrewLoginSevlet
 */
@WebServlet("/CrewLoginServlet")
public class CrewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrewLoginServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Crew crew = new Crew();
		CrewRegistrationLogin clr =new CrewRegistrationLogin();
		crew=clr.login(Integer.parseInt(request.getParameter("crewId")),request.getParameter("password"));
		if(crew != null) {
			PrintWriter out = response.getWriter();
			out.println(crew.getCrewId());
		session.setAttribute("crewId", request.getParameter("crewId"));
		if(session.getAttribute("url")!=null) {
			session.setAttribute("crew", crew);
			session.setAttribute("id", crew.getCrewId());
			session.setAttribute("name", crew.getName());
			session.setAttribute("contactno", crew.getContactNo());
			session.setAttribute("designation", crew.getDesignation());
			session.setAttribute("leavedays", crew.getLeaveDays());
			response.sendRedirect((String) session.getAttribute("url"));
		}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("crewLogin.jsp");
			rd.include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<h2>Invalid Username or Password</h2>");
			
		}
	}
	

}
