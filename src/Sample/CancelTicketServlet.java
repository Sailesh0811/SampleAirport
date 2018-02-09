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

import coda.global.airport.CustomerImplementation;
import coda.global.bean.Customer;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = new Customer();
		CustomerImplementation cust = new CustomerImplementation();
		customer=(Customer) session.getAttribute("customer");
		
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
