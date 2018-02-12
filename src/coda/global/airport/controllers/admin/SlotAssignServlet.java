package coda.global.airport.controllers.admin;

import java.io.IOException;
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
import coda.global.bean.Crew;
import coda.global.bean.Leave;

/**
 * Servlet implementation class SlotAssignServlet
 */
@WebServlet("/Admin/SlotAssignServlet")
public class SlotAssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SlotAssignServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminImplementation ai = new AdminImplementation();
		List<Crew> slotList= new LinkedList<Crew>();
		int scheduleFlight=0;
		slotList=null;
		if(request.getParameter("scheduleFlightNo")!=null) {
			scheduleFlight = Integer.parseInt(request.getParameter("scheduleFlightNo"));
			slotList=ai.slotAssign(scheduleFlight);
			if(slotList==null) {
				
				request.setAttribute("check", 0);
			}
			else {
				session.setAttribute("scheduleFlightNo", scheduleFlight);
				System.out.println("ddafdaa");
				request.setAttribute("check", 1);
				request.setAttribute("slot", slotList);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("slotAssign.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminImplementation ai = new AdminImplementation();
		List<Crew> slotList= new LinkedList<Crew>();
		int scheduleFlight=0;
		scheduleFlight = ((Integer)session.getAttribute("scheduleFlightNo"));	
		System.out.println(scheduleFlight);
		slotList=ai.slotAssign(scheduleFlight);
				for(int i=0;i<slotList.size();i++) {
					System.out.println(slotList.get(i).getCrewId());
					String p="approve"+slotList.get(i).getCrewId();
					System.out.println("faefadfd"+p);
					if(request.getParameter(p)!=null) {
					String resp = request.getParameter(p);
					System.out.println(resp);
					if(resp.contains("approve")) {
						ai.slotInsert(scheduleFlight, slotList.get(i).getCrewId(), slotList.get(i).getStatus());
					}
					else {
						ai.denySlot(scheduleFlight, slotList.get(i).getCrewId(),slotList.get(i).getStatus());
					}
					}
					else {
						
						System.out.println("fadfadfsggsg");
					}
					
				}
	
			
		
		
		RequestDispatcher rd = request.getRequestDispatcher("slotAssign.jsp");
		rd.include(request, response);
	}

}
