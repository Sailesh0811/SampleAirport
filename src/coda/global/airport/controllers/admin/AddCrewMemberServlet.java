package coda.global.airport.controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coda.global.airport.dao.CrewRegistrationLogin;
import coda.global.airport.delegates.AdminDelegate;
import coda.global.bean.Crew;

/**
 * Servlet implementation class AddCrewMemberServlet
 */
@WebServlet("/Admin/AddCrewMemberServlet")
public class AddCrewMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCrewMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("dafdffadf");
		HttpSession session = request.getSession();
		if ((((Integer)session.getAttribute("adminId"))==1)) {
			Crew crew = new Crew();
			AdminDelegate adminDelegate = new AdminDelegate();
			crew.setContactNo(Integer.parseInt(request.getParameter("contactno")));
			crew.setName(request.getParameter("name"));
			crew.setGender(request.getParameter("gender"));
			crew.setDesignation(request.getParameter("designation"));
			crew.setPassword(request.getParameter("password"));
			String l = request.getParameter("languages");
			String[] languages = l.split(",");
			String result = adminDelegate.register(crew, languages);
			RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
			rd.include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<h1>" + result + "</h1>");
		} else {
			session.setAttribute("url", "addCrewMember.jsp");
			response.sendRedirect("AdminLogin.jsp");
		}
	}

}
