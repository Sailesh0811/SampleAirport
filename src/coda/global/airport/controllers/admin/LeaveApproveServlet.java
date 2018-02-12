package coda.global.airport.controllers.admin;

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

import coda.global.airport.dao.AdminImplementation;
import coda.global.bean.Flight;
import coda.global.bean.Leave;

/**
 * Servlet implementation class LeaveApproveServlet
 */
@WebServlet("/Admin/LeaveApproveServlet")
public class LeaveApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminImplementation ai = new AdminImplementation();
		List<Leave> leaveList= new LinkedList<Leave>();
		leaveList=ai.leaveApproval();
		if(leaveList==null) {
			request.setAttribute("check", 0);
		}
		else {
			System.out.println("ddafdaa");
			request.setAttribute("check", 1);
			request.setAttribute("leave", leaveList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("leaveApprove.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if ((((Integer)session.getAttribute("adminId"))==1)) {			
			AdminImplementation ai = new AdminImplementation();
			List<Leave> leaveList= new LinkedList<Leave>();
			leaveList=ai.leaveApproval();
			for(int i=0;i<leaveList.size();i++) {
				System.out.println(leaveList.get(i).getId());
				String p="approve"+leaveList.get(i).getId();
				System.out.println("faefadfd"+p);
				if(request.getParameter(p)!=null) {
				String resp = request.getParameter(p);
				System.out.println(resp);
				if(resp.contains("approve")) {
					ai.approveLeave(leaveList.get(i).getId());
				}
				else {
					ai.denyLeave(leaveList.get(i).getId());
				}
				}
				else {
					System.out.println("fadfadfsggsg");
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("leaveApprove.jsp");
			rd.include(request, response);
			
		} else {
			session.setAttribute("url", "LeaveApproveServlet.jsp");
			response.sendRedirect("AdminLogin.jsp");
		}
	}

}
