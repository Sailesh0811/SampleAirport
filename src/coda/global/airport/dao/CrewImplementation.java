package coda.global.airport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import coda.global.bean.Crew;
import coda.global.bean.Flight;
import coda.global.utility.Database;

public class CrewImplementation implements CrewInterface {
	Crew crew = new Crew();
	Scanner scan = new Scanner(System.in);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat tf = new SimpleDateFormat("hh:mm");
	// public CrewImplementation() {
	// start();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#start()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#viewProfile()
	 */

	public Crew viewProfile(Crew crew) {

		return crew;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#assignedDuty()
	 */

	public List<Crew> assignedDuty(Crew crew) throws SQLException {
		List<Crew> crewList = new LinkedList<Crew>();
		Connection con = new Database().getConnection();
		System.out.println("1.Your duty 2.Other crew members ");
		PreparedStatement dutyStatement;

		dutyStatement = con.prepareStatement("select * from slot where crew_id =?");
		dutyStatement.setInt(1, crew.getCrewId());
		ResultSet dutySet = dutyStatement.executeQuery();

		while (dutySet.next()) {
			Crew tempCrew = new Crew();
			tempCrew.setScheduleFlightno(dutySet.getInt(1));

			int flightScheduleNo = dutySet.getInt(1);
			System.out.println(flightScheduleNo);
			PreparedStatement crewDuty = con.prepareStatement("select * from slot where flight_schedule_no=?");
			crewDuty.setInt(1, flightScheduleNo);
			ResultSet crewDutySet = crewDuty.executeQuery();
			while (crewDutySet.next()) {
				PreparedStatement logincrew = con.prepareStatement(
						"select id,crew_name,gender,contact_no,password,designation from crew where  id =?");
				logincrew.setInt(1, crewDutySet.getInt(2));
				ResultSet cust = logincrew.executeQuery();
				while (cust.next()) {
					System.out.println(cust.getInt(1));
					tempCrew.setCrewId(cust.getInt(1));
					tempCrew.setName(cust.getString(2));
					tempCrew.setGender(cust.getString(3));
					tempCrew.setContactNo(cust.getInt(4));
					// System.out.println(cust.getString(5));
					tempCrew.setDesignation(cust.getString(6));
					crewList.add(tempCrew);
				}
				System.out.println();
			}
		}

		return crewList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#slotRequest()
	 */

	public String slotRequest(Crew crew, String d, String destination) throws ParseException, SQLException {
		Connection con = new Database().getConnection();
		Date date = new Date();
		String result = "";
		date = df.parse(d);
		System.out.println("Enter the destination you want?");

		HashSet<Integer> flightList = new HashSet<Integer>();
		PreparedStatement slotFlight = con.prepareStatement(
				"select f.flight_no,f.airline,fs.departure,fs.arrival,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.destination =? and f.id = fs.flight_id and fs.date = ? ");
		slotFlight.setString(1, destination);
		slotFlight.setDate(2, new java.sql.Date(date.getTime()));
		ResultSet searchResult = slotFlight.executeQuery();
		boolean flightFoundFlag = false;
		while (searchResult.next()) {
			flightFoundFlag = true;
			flightList.add(searchResult.getInt(5));
			result += (searchResult.getInt(5) + ",");
		}
		if (!flightFoundFlag) {
			result += ("error");

		}

		return result;
	}

	public String insertSlot(Crew crew, int prefFlightNo) throws SQLException {
		Connection con = new Database().getConnection();
		String result = "";
		PreparedStatement insertSlotRequest;

		insertSlotRequest = con
				.prepareStatement("insert into slot_requests (flight_schedule_no,crew_id,status) values (?,?,?)");
		insertSlotRequest.setInt(1, prefFlightNo);
		insertSlotRequest.setInt(2, crew.getCrewId());
		insertSlotRequest.setString(3, "PENDING");
		if (!insertSlotRequest.execute()) {
			result += ("Your request has been sent");
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#leaveRequest()
	 */

	public String leaveRequest(Crew crew, String d, String[] cd, int noOfDays) throws ParseException, SQLException {
		Connection con = new Database().getConnection();
		String result = "";
		Date date = new Date();

		date = df.parse(d);

		System.out.println("Enter the number of days max of 2");

		Date[] compesanateDate = new Date[2];

		for (int i = 0; i < noOfDays; i++) {

			compesanateDate[i] = df.parse(cd[i]);

		}

		PreparedStatement updateAvailablity = con.prepareStatement("insert into crew_availability values (?,?,?)");
		for (int i = 0; i < noOfDays; i++) {
			updateAvailablity.setInt(1, crew.getCrewId());
			updateAvailablity.setDate(2, new java.sql.Date(compesanateDate[i].getTime()));
			updateAvailablity.setString(3, "A");
			updateAvailablity.execute();
		}
		updateAvailablity = con.prepareStatement("insert into crew_availability values (?,?,?)");
		for (int i = 0; i < noOfDays; i++) {
			updateAvailablity.setInt(1, crew.getCrewId());
			if (i == 0)
				updateAvailablity.setDate(2, new java.sql.Date(date.getTime()));
			else
				updateAvailablity.setDate(2,
						new java.sql.Date((new Date(date.getTime() + (1000 * 60 * 60 * 24)).getTime())));
			updateAvailablity.setString(3, "NA");
			updateAvailablity.execute();
		}
		result += ("your request has been sent");

		return result;

	}

	public String insertLeave(Crew crew, String d, int noOfDays) throws ParseException, SQLException {
		Connection con = new Database().getConnection();
		String result = "";
		Date date = new Date();

		System.out.println("Enter the date yyyy-mm-dd");
		
			date = df.parse(d);

		
		
			PreparedStatement insertLeave = con
					.prepareStatement("insert into leave_requests (crew_id,date,no_of_days,status) values (?,?,?,?) ");
			insertLeave.setInt(1, crew.getCrewId());
			insertLeave.setDate(2, new java.sql.Date(date.getTime()));
			insertLeave.setInt(3, noOfDays);
			insertLeave.setString(4, "REQ");
			if (!insertLeave.execute()) {

				result += ("Requested!!");
			}
		
		return result;
	}
}
