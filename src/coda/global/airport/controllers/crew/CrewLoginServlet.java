package coda.global.airport.controllers.crew;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import coda.global.airport.dao.CrewRegistrationLogin;
import coda.global.bean.Crew;

/**
 * Servlet implementation class CrewLoginSevlet
 */
@WebServlet("/Crew/CrewLoginServlet")
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
		JSONObject json = new JSONObject();
		System.out.println("fadfadda");
		CrewRegistrationLogin clr =new CrewRegistrationLogin();
		crew=clr.login(Integer.parseInt(request.getParameter("crewId")),request.getParameter("password"));
		if(crew != null) {
			
		session.setAttribute("crewId", request.getParameter("crewId"));
//		if(session.getAttribute("url")!=null) {
			session.setAttribute("crew", crew);
			session.setAttribute("id", crew.getCrewId());
			session.setAttribute("name", crew.getName());
			session.setAttribute("contactno", crew.getContactNo());
			session.setAttribute("designation", crew.getDesignation());
			session.setAttribute("leavedays", crew.getLeaveDays());
			//response.sendRedirect((String) session.getAttribute("url"));
			try {
				json.put("status", "success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		}else {
			try {
				json.put("status", "failed");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setContentType("application/javascript");
		PrintWriter out = response.getWriter();
		out.println(json);
	}
	

}
