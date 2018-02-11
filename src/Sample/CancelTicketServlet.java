package Sample;

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

import coda.global.airport.CustomerImplementation;
import coda.global.bean.Customer;
import coda.global.bean.Transaction;

/**
 * Servlet implementation class CancelTicketServlet
 */
@WebServlet("/CancelTicketServlet")
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
		transactionList=cust.viewHistory(customer, "2", 0);
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
		RequestDispatcher rd = request.getRequestDispatcher("Customer.jsp");
		rd.include(request, response);
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>window.alert(" +result+ ") </script>");
	}

}
