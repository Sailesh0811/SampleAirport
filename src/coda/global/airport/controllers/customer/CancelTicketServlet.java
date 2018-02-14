package coda.global.airport.controllers.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import coda.global.airport.dao.CustomerImplementation;
import coda.global.bean.Customer;
import coda.global.bean.Transaction;

/**
 * Servlet implementation class CancelTicketServlet
 */
@WebServlet("/Customer/CancelTicketServlet")
public class CancelTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("customerId") != null) {
			System.out.println("Customer id" + (String) session.getAttribute("customerId"));

			//response.sendRedirect("book.jsp");			
		} else {
			session.setAttribute("url", "CancelTicketServlet");
			response.sendRedirect("login.jsp");
		}
		Customer customer = new Customer();
		CustomerImplementation cust = new CustomerImplementation();
		customer=(Customer) session.getAttribute("customer");
		List<Transaction> transactionList=new LinkedList<Transaction>();		
		transactionList=cust.viewHistory(customer, "3", 0);
		request.setAttribute("transaction", transactionList);
		RequestDispatcher rd = request.getRequestDispatcher("cancelTicket.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = new Customer();
		CustomerImplementation cust = new CustomerImplementation();
		customer=(Customer) session.getAttribute("customer");
		List<Transaction> transactionList=new LinkedList<Transaction>();		
		transactionList=cust.viewHistory(customer, "2", 0);
		int pnr = Integer.parseInt((String)request.getParameter("pnr"));
		System.out.println(pnr);
		String result=cust.cancelTicket(customer, pnr);
		System.out.println(result);
//		RequestDispatcher rd = request.getRequestDispatcher("Customer.jsp");
//		rd.include(request, response);
		response.setContentType("application/javascript");
		PrintWriter out = response.getWriter();
		JSONObject json=new JSONObject();
		try {
			if(result.equals("success"))
				json.put("status", "success");
			else
				json.put("status", "error");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		out.println(json);
	}

}
