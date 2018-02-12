package coda.global.airport.delegates;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import coda.global.airport.dao.CrewImplementation;
import coda.global.bean.Crew;

public class CrewDelegate {
	public Crew viewProfile(Crew crew) {
		
		return new CrewImplementation().viewProfile(crew);
	}
	public List<Crew> assignedDuty(Crew crew){
		CrewImplementation crewImplementation = new CrewImplementation();
		List<Crew> crewList = new LinkedList<Crew>();
		try {
			crewList= crewImplementation.assignedDuty(crew);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return crewList;
	}
	public String slotRequest(Crew crew, String d, String destination) {
		CrewImplementation crewImplementation = new CrewImplementation();
		String result ="";
		try {
			 result = crewImplementation.slotRequest(crew, d, destination);
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String insertSlot(Crew crew, int prefFlightNo) {
		CrewImplementation crewImplementation = new CrewImplementation();
		String result ="";
		try {
			result = crewImplementation.insertSlot(crew, prefFlightNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String leaveRequest(Crew crew, String d, String[] cd, int noOfDays) {
		CrewImplementation crewImplementation = new CrewImplementation();
		String result ="";
		try {
			result = crewImplementation.leaveRequest(crew, d, cd, noOfDays);
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String insertLeave(Crew crew, String d, int noOfDays) {
		CrewImplementation crewImplementation = new CrewImplementation();
		String result ="";
		try {
			result = crewImplementation.insertLeave(crew, d, noOfDays);
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
