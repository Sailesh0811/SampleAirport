package coda.global.airport;

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
	@Override
	public void start() {
		// crew = new CrewRegistrationLogin().login();

		// char choice = 'Y';
		// do {
		// System.out.println("1. View Profile 2.Assigned Duty 3.Slot Request 4.Leave
		// Request 5.exit");
		// switch (scan.nextInt()) {
		// case 1: viewProfile();
		// break;
		// case 2: assignedDuty();
		// break;
		// case 3: slotRequest();
		// break;
		// case 4: leaveRequest();
		// break;
		// case 5:
		// choice = 'N';
		// break;
		// default:
		// System.out.println("Enter a proper choice");
		// break;
		// }
		// } while (choice == 'Y');
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#viewProfile()
	 */
	@Override
	public String viewProfile(Crew crew) {
		String res = "";
		res += (crew.getName());
		res += ("," + crew.getCrewId());
		res += ("," + crew.getContactNo());
		res += ("," + crew.getDesignation());
		res += ("," + crew.getLeaveDays());
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#assignedDuty()
	 */
	@Override
	public String[] assignedDuty(Crew crew) {
		String[] result = new String[2];
		Connection con = new Database().getConnection();
		System.out.println("1.Your duty 2.Other crew members ");
		PreparedStatement dutyStatement;
		result[0]="";result[1]="";
		try {
			dutyStatement = con.prepareStatement("select * from slot where crew_id =?");
			dutyStatement.setInt(1, crew.getCrewId());
			ResultSet dutySet = dutyStatement.executeQuery();
			boolean flag = true;
			while (dutySet.next()) {
				result[0] += ("Scheduled Flight No: " + dutySet.getInt(1)+",");
				System.out.println("inside creeim"+result[0]);
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
						result[1]+=(cust.getInt(1)+",");
						//(cust.getString(2)+",");
						//(cust.getString(3)+",");
						//(cust.getInt(4)+",");
						// System.out.println(cust.getString(5));
						//(cust.getString(6)+"\n");

					}
					System.out.println();
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#slotRequest()
	 */
	@Override
	public String slotRequest(Crew crew, String d, String destination) {
		Connection con = new Database().getConnection();
		Date date = new Date();
		String result = "";

		try {
			date = df.parse(d);

		} catch (ParseException e) {
			result += ("Enter a proper date ");
		}

		System.out.println("Enter the destination you want?");

		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String insertSlot(Crew crew, int prefFlightNo) {
		Connection con = new Database().getConnection();
		String result = "";
		PreparedStatement insertSlotRequest;
		try {
			insertSlotRequest = con
					.prepareStatement("insert into slot_requests (flight_schedule_no,crew_id,status) values (?,?,?)");
			insertSlotRequest.setInt(1, prefFlightNo);
			insertSlotRequest.setInt(2, crew.getCrewId());
			insertSlotRequest.setString(3, "PENDING");
			if (!insertSlotRequest.execute()) {
				result += ("Your request has been sent");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CrewInterface#leaveRequest()
	 */
	@Override
	public String leaveRequest(Crew crew, String d, String[] cd, int noOfDays) {
		Connection con = new Database().getConnection();
		String result = "";
		Date date = new Date();

		try {
			date = df.parse(d);

		} catch (ParseException e) {
			System.out.println("Enter a proper date ");

		}

		System.out.println("Enter the number of days max of 2");

		Date[] compesanateDate = new Date[2];

		for (int i = 0; i < noOfDays; i++) {
			while (true) {

				try {
					compesanateDate[i] = df.parse(cd[i]);
					break;
				} catch (ParseException e) {
					System.out.println("Enter a proper date ");
				}
			}
		}
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public String insertLeave(Crew crew, String d, int noOfDays) {
		Connection con = new Database().getConnection();
		String result = "";
		Date date = new Date();

		System.out.println("Enter the date yyyy-mm-dd");
		try {
			date = df.parse(d);

		} catch (ParseException e) {
			System.out.println("Enter a proper date ");
		}

		try {
			PreparedStatement insertLeave = con
					.prepareStatement("insert into leave_requests (crew_id,date,no_of_days,status) values (?,?,?,?) ");
			insertLeave.setInt(1, crew.getCrewId());
			insertLeave.setDate(2, new java.sql.Date(date.getTime()));
			insertLeave.setInt(3, noOfDays);
			insertLeave.setString(4, "REQ");
			if (!insertLeave.execute()) {

				result += ("Requested!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
