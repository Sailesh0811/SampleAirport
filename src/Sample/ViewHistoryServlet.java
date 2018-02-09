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
 * Servlet implementation class ViewHistoryServlet
 */
@WebServlet("/ViewHistoryServlet")
public class ViewHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHistoryServlet() {
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
		List<Transaction> transactionList=new LinkedList<Transaction>();
		customer=(Customer) session.getAttribute("customer");
		String choice = request.getParameter("choice");
		System.out.println(choice);
		int pnr=0;
		if(choice.equals("1")) {
		 pnr = Integer.parseInt((String)request.getParameter("pnr"));
		
		}
		System.out.println(pnr);
		transactionList=cust.viewHistory(customer, choice, pnr);
		if(transactionList==null) {
			request.setAttribute("check", 0);
		}else {
			request.setAttribute("check", 1);
		}
		request.setAttribute("transaction", transactionList);
		RequestDispatcher rd = request.getRequestDispatcher("viewHistory.jsp");
		rd.include(request, response);
		
	}

}
