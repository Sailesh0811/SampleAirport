package coda.global.airport.controllers.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import coda.global.airport.dao.CustomerLoginRegister;
import coda.global.airport.delegates.CustomerDelegate;
import coda.global.bean.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Customer/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Customer cust = new Customer();
		CustomerDelegate clr = new CustomerDelegate();
		JSONObject json = new JSONObject();
		cust = clr.login(Integer.parseInt(request.getParameter("customerId")), request.getParameter("password"));
		if (cust != null) {
			PrintWriter out = response.getWriter();
			//out.println(cust.getCustomerId());
			session.setAttribute("customerId", request.getParameter("customerId"));
			// if(session.getAttribute("url")!=null) {
			session.setAttribute("customer", cust);
			// response.sendRedirect((String) session.getAttribute("url"));
			
			response.setContentType("application/json");
			try {
				json.put("status", "success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//			rd.include(request, response);
			

		}
		else {
			try {
				json.put("status", "failed");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter out = response.getWriter();
		out.println(json);
	}

}
