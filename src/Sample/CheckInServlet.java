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
 * Servlet implementation class CheckInServlet
 */
@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInServlet() {
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
		String[] seats=cust.checkIn(customer, pnr);
		System.out.println("seats"+seats[0]);
		System.out.println(seats[1]);
		RequestDispatcher rd = request.getRequestDispatcher("checkIn.jsp");
		rd.include(request, response);
		PrintWriter out = response.getWriter();
		out.println(seats[1]);
		session.setAttribute("seats", seats[0]);
		session.setAttribute("pnr", seats[2]);
		out.println("<form method='post' action='AddSeatServlet'>");
		for(int i=0;i<Integer.parseInt(seats[0]);i++) {
			out.println("Add seat for passenger "+(i+1));
			String s="seat"+i;
			out.println("<input typr='text' name="+s+">");
		}
		out.println("<input type='submit' value='AddSeat' name='submit'>");
		out.print("</form>");
	}

}
