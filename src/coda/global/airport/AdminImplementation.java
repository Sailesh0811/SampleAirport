package coda.global.airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import coda.global.bean.Crew;
import coda.global.bean.Flight;
import coda.global.bean.Leave;
import coda.global.utility.Database;

public class AdminImplementation implements AdminInterface  {
	static int password = 1234;
	Scanner scan = new Scanner(System.in);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat tf = new SimpleDateFormat("hh:mm:ss");

	
	void start() {
		System.out.println("Enter the password!");
		if (scan.nextInt() == password) {
			char choice = 'Y';
			do {
				System.out.println(
						"1. Add crew member 2.Add Flight 3.Add Seats 4.Schedule Flight 5.Slot Assign 6.Leave approval 7.View Added flights 8.View Scheduled flight  9.Exit");
				switch (scan.nextInt()) {
				case 1:
					addCrewMember();
					break;
				case 2:
					//addFlight();
					break;
				case 3:
					//addSeats();
					break;
				case 4:
					//scheduleFlight();
					break;
				case 5:
//					slotAssign();
					break;
				case 6:
					leaveApproval();
					break;
				case 7:
					viewAddedFlight();
					break;
				case 8:
					viewScheduledFlight();
					break;
				case 9:
					choice ='N';
					break;
				default:System.out.println("Enter a proper choice");
				}
			} while (choice == 'Y');
		}
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#addCrewMember()
	 */
	@Override
	public void addCrewMember() {
//		CustomerLoginRegister cust = new CustomerLoginRegister();
//		cust.register();
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#addFlight()
	 */
	@Override
	public String addFlight(Flight flight) {
		Database db = new Database();
		Connection con = db.getConnection();
		String result="";

		try {
			PreparedStatement addFlight = con.prepareStatement(
					"insert into flight (flight_no,airline,boarding_place,destination) values (?,?,?,?)");
			addFlight.setString(1, flight.getFlightNo());
			addFlight.setString(2, flight.getAirline());
			addFlight.setString(3, flight.getFrom());
			addFlight.setString(4, flight.getTo());
			if (!addFlight.execute()) {
				result+=("Flight has been added");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#addSeats()
	 */
	@Override
	public String addSeats(Flight flight) {
		//viewAddedFlight();
		Database db = new Database();
		Connection con = db.getConnection();
		String result="";
		try {
			PreparedStatement addSeats = con.prepareStatement("insert into seats values(?,?,?,?) ");
			
			addSeats.setInt(1, flight.getId());
			addSeats.setString(2, "EC");
			
			addSeats.setString(3, ""+flight.getStartEconomySeatNo());
			System.out.println("Enter the ending seat number for economy class");
			addSeats.setString(4, ""+flight.getEndEconomySeatNo());
			addSeats.execute();
			addSeats = con.prepareStatement("insert into seats values(?,?,?,?) ");
			addSeats.setInt(1, flight.getId());
			addSeats.setString(2, "BC");
			System.out.println("Enter the starting seat number for business class");
			addSeats.setString(3, ""+flight.getStartBusinessSeatNo());
			System.out.println("Enter the ending seat number for business class");
			addSeats.setString(4, flight.getEndBusinessSeatNo()+"");
			if (!addSeats.execute()) {
				result+=("Seats are added ");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#scheduleFlight()
	 */
	@Override
	public String scheduleFlight(Flight flight,String atime,String dtime,String dat) {
		Database db = new Database();
		Connection con = db.getConnection();
		String result="";
		Date date;
		System.out.println("Enter the flight id");
		
		while (true) {
			System.out.println("Enter the date");
			try {
				flight.setDate(df.parse(dat));
				break;
			} catch (ParseException e) {
				System.out.println("Enter proper date format");
			}
		}
		while (true) {
			System.out.println("Enter the departure time");
			try {
				flight.setDeparture(tf.parse(dtime));
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (true) {
			System.out.println("Enter the arrival time");
			try {
				flight.setArrival(tf.parse(atime));
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			PreparedStatement scheduleFlight = con.prepareStatement(
					"insert into flight_schedule (flight_id,date,departure,arrival,economy_seats,economy_fare,business_seats,business_fare ) values (?,?,?,?,?,?,?,?)");
			scheduleFlight.setInt(1, flight.getId());
			scheduleFlight.setDate(2, new java.sql.Date(flight.getDate().getTime()));
			scheduleFlight.setTime(3, new java.sql.Time(flight.getDeparture().getTime()));
			scheduleFlight.setTime(4, new java.sql.Time(flight.getArrival().getTime()));
			scheduleFlight.setInt(5, flight.getEconomySeats());
			scheduleFlight.setFloat(6, flight.getEconomyFare());
			scheduleFlight.setInt(7, flight.getBusinessSeats());
			scheduleFlight.setFloat(8, flight.getBusinessFare());
			if (!scheduleFlight.execute()) {
				result+=("Your flight has been scheduled");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#leaveApproval()
	 */
	@Override
	public List<Leave> leaveApproval() {
		Database db = new Database();
		Connection con = db.getConnection();
		List<Leave> leaveList = new LinkedList<Leave>();
		boolean flag = true;
		try {
			PreparedStatement leaveRequest = con.prepareStatement("select * from leave_requests where status =?");
			leaveRequest.setString(1, "REQ");
			ResultSet leaveSet = leaveRequest.executeQuery();
			while (leaveSet.next()) {
				Leave leave= new Leave();
				 leave.setId(leaveSet.getInt(1));
				leave.setCrewId(leaveSet.getInt(2));				
				leave.setDate(leaveSet.getDate(3));				
				leave.setNoOfDays(leaveSet.getInt(4));				
				leave.setStatus(leaveSet.getString(5)+"\n");
				flag = false;				
				leaveList.add(leave);
			}
			if (flag) {
				leaveList=null;
			} else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leaveList;

	}
	public String approveLeave(int id) {
		Database db = new Database();
		Connection con = db.getConnection();
		System.out.println("Enter the id you want to approve");
		String result="";
		PreparedStatement leaveApprove;
		try {
			leaveApprove = con.prepareStatement("update leave_requests set status=? where id=?");
			leaveApprove.setString(1, "APPROVED");
			leaveApprove.setInt(2, id);
			if (!leaveApprove.execute()) {
				PreparedStatement ps = con.prepareStatement("select * from leave_requests where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				int noOfDays=0;
				int crewId=0;
				Date date=new Date();
				while(rs.next()) {
					crewId=rs.getInt(2);
					noOfDays=rs.getInt(4);
					date=rs.getDate(3);
				}
				PreparedStatement updateAvailablity = con.prepareStatement("insert into crew_availability values (?,?,?)");
					for(int i=0;i<noOfDays;i++) {
						updateAvailablity.setInt(1,crewId);
						if(i==0)
							updateAvailablity.setDate(2, new java.sql.Date(date.getTime()));
						else
							updateAvailablity.setDate(2, new java.sql.Date((new Date(date.getTime() + (1000 * 60 * 60 * 24)).getTime())));
						updateAvailablity.setString(3, "NA");
						updateAvailablity.execute();
					}
				result+=("Approved!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
		
	}
	public String denyLeave(int id) {
		Database db = new Database();
		Connection con = db.getConnection();
		System.out.println("Enter the id you want to approve");
		String result="";
		PreparedStatement leaveApprove;
		try {
			leaveApprove = con.prepareStatement("update leave_requests set status=? where id=?");
			leaveApprove.setString(1, "DENY");
			leaveApprove.setInt(2, id);
			if (!leaveApprove.execute()) {
				result+="Denied";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#viewAddedFlight()
	 */
	@Override
	public void viewAddedFlight() {
		Database db = new Database();
		Connection con = db.getConnection();
		boolean flag = true;

		try {
			PreparedStatement addedFlight = con.prepareStatement("select * from flight");
			ResultSet flightList = addedFlight.executeQuery();
			while (flightList.next()) {
				System.out.print("Id: " + flightList.getInt(1));
				System.out.print(" Flight No" + flightList.getString(2));
				System.out.print(" Airline: " + flightList.getString(3));
				System.out.print(" Origin: " + flightList.getString(4));
				System.out.println(" Destination: " + flightList.getString(5));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#viewScheduledFlight()
	 */
	@Override
	public void viewScheduledFlight() {
		Database db = new Database();
		Connection con = db.getConnection();
		boolean flag = true;
		Date date;
		System.out.println("Enter the date");
		while (true) {
			System.out.println("Enter the date");
			try {
				date = df.parse(scan.next());
				break;
			} catch (ParseException e) {
				System.out.println("Enter proper date format");
			}
		}

		try {
			PreparedStatement scheduledFlight = con.prepareStatement("select * from flight_schedule where date=?");
			scheduledFlight.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet scheduledList = scheduledFlight.executeQuery();
			while (scheduledList.next()) {
				System.out.println(" Scheduled flight no: " + scheduledList.getInt(1));
				System.out.print(" flight id: " + scheduledList.getInt(2));
				System.out.println(" Date : " + scheduledList.getDate(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see coda.global.airport.AdminInterface#slotAssign()
	 */
	@Override
	public List<Crew> slotAssign(int scheduledFlight) {
		List<Crew> slotList = new LinkedList<Crew>();
		Database db = new Database();
		Connection con = db.getConnection();
		
		boolean foundFlag = true;
		System.out.println("Enter the scheduled flight number you want to assign crew");		
		int crew_id = 0;
		Date date = new Date();
		try {
			PreparedStatement getDate = con
					.prepareStatement("select date from flight_schedule where flight_schedule_no =?");
			getDate.setInt(1, scheduledFlight);
			ResultSet rs = getDate.executeQuery();

			while (rs.next()) {
				date = rs.getDate(1);
			}
			/* showing crew applied for compensation in this date */
			PreparedStatement compensation = con.prepareStatement(
					"select ca.crew_id,c.crew_name,c.designation from crew_availability ca  inner join  crew c on ca.crew_id=c.id where ca.date=? and ca.status = ?;");
			compensation.setDate(1, new java.sql.Date(date.getTime()));
			compensation.setString(2, "A");
			ResultSet compensationList = compensation.executeQuery();
			while (compensationList.next()) {
				Crew crew = new Crew();
				foundFlag = false;
				crew_id = compensationList.getInt(1);
				crew.setCrewId(compensationList.getInt(1));
				crew.setDesignation(compensationList.getString(3));
				crew.setStatus("COMP");	
				slotList.add(crew);
					System.out.println("Do you want to allow him to compensate (Y/N)");
//					if (scan.next().equalsIgnoreCase("Y")) {
//						PreparedStatement assignSlot = con.prepareStatement("insert into slot values (?,?)");
//						assignSlot.setInt(1, scheduledFlight);
//						assignSlot.setInt(2, crew_id);
//						if (!assignSlot.execute()) {
//							PreparedStatement updateAvailablity = con.prepareStatement(
//									"update crew_availability set status=? where crew_id =? and date=?");
//							updateAvailablity.setString(1, "NA");
//							updateAvailablity.setInt(2, crew_id);
//							updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//							if (!updateAvailablity.execute()) {
//								System.out.println("Slot has been assigned");
//							}
//						}
//						break;
//					} else if (scan.next().equalsIgnoreCase("N")) {
//						PreparedStatement updateAvailablity = con
//								.prepareStatement("update crew_availability set status=? where crew_id =? and date=?");
//						updateAvailablity.setString(1, "NA");
//						updateAvailablity.setInt(2, crew_id);
//						updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//						if (!updateAvailablity.execute()) {
//							PreparedStatement increaseLeave = con
//									.prepareStatement("update crew set leave_count+=? where id=? ");
//							increaseLeave.setInt(1, 1);
//							increaseLeave.setInt(2, crew_id);
//							if (!increaseLeave.execute())
//								System.out.println("Slot has not been assigned");
//						}
//						
					
				}
			
			/* getting slot requests */
			PreparedStatement slotRequests = con
					.prepareStatement("select * from slot_requests where flight_schedule_no=? and status =?");
			slotRequests.setInt(1, scheduledFlight);
			slotRequests.setString(2, "REQ");
			ResultSet slotSet = slotRequests.executeQuery();
			while (slotSet.next()) {
				Crew crew = new Crew();
				crew_id = slotSet.getInt(1);
				crew.setCrewId( slotSet.getInt(1));
				crew.setDesignation(slotSet.getString(2));
				crew.setStatus("NON");
				slotList.add(crew);
			}
					System.out.println("Do you want to assign slot (Y?N)");
//					if (scan.next().equalsIgnoreCase("Y")) {
//						PreparedStatement assignSlot = con.prepareStatement("insert into slot values (?,?)");
//						assignSlot.setInt(1, scheduledFlight);
//						assignSlot.setInt(2, crew_id);
//						if (!assignSlot.execute()) {
//							PreparedStatement updateAvailablity = con.prepareStatement(
//									"update crew_availability set status=? where crew_id =? and date=?");
//							updateAvailablity.setString(1, "NOT");
//							updateAvailablity.setInt(2, crew_id);
//							updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//							if (!updateAvailablity.execute()) {
//								System.out.println("Slot has been assigned");
//							}
//						}
//						break;
//					} else if (scan.next().equalsIgnoreCase("N")) {
//						PreparedStatement updateAvailablity = con
//								.prepareStatement("update slot_requests set status=? where crew_id =? and flight_schedule_no=?");
//						updateAvailablity.setString(1, "REJ");
//						updateAvailablity.setInt(2, crew_id);
//						updateAvailablity.setInt(3, scheduledFlight);
//						if(!updateAvailablity.execute()) {
//							System.out.println("Slot not assigned!");
//						}
//						break;
//					} else {
//						System.out.println("Enter a proper choice");
//					}
//				}
			
			/* displaying general crew */
			
				
					PreparedStatement moreCrew = con.prepareStatement("select * from crew c where  c.id!=ALL(select slot.crew_id from slot where c.id=slot.crew_id and  flight_schedule_no=?);");
					moreCrew.setInt(1, scheduledFlight);
					ResultSet moreCrewSet = moreCrew.executeQuery();
					while(moreCrewSet.next()) {
						Crew crew = new Crew();
						PreparedStatement checkAvail = con.prepareStatement("select * from crew_availability ca where ca.crew_id=? and ca.status!=?");
						checkAvail.setInt(1,moreCrewSet.getInt(1) );
						checkAvail.setString(2, "NA");
						ResultSet availList = checkAvail.executeQuery();
						if(availList.next()) {
							continue;						
						}else {
						crew.setCrewId(moreCrewSet.getInt(1));						
						crew.setDesignation(moreCrewSet.getString(3));
						crew.setStatus("NOR");
						}
						slotList.add(crew);
					}
//					System.out.println("Enter the crew id to assign");
//					PreparedStatement assignCrew = con.prepareStatement("Insert into slot values (?,?)");
//					assignCrew.setInt(1, scheduledFlight);
//					assignCrew.setInt(2, scan.nextInt());
//					if(!assignCrew.execute()) {
//						System.out.println("Slot assigned");
//					}				
				
			

		 }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return slotList;
		
	}
	public void slotInsert(int schedule,int crewId,String type) {
		Database db = new Database();
		Connection con = db.getConnection();
		switch(type){
			case "COMP": 
			try {
				PreparedStatement assignSlot = con.prepareStatement("insert into slot values (?,?)");
				assignSlot.setInt(1, schedule);
				assignSlot.setInt(2, crewId);
				if (!assignSlot.execute()) {
//					PreparedStatement updateAvailablity = con.prepareStatement(
//							"update crew_availability set status=? where crew_id =? and date=?");
//					updateAvailablity.setString(1, "NA");
//					updateAvailablity.setInt(2, crewId);
//					updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//					if (!updateAvailablity.execute()) {
//						System.out.println("Slot has been assigned");
//					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				break;
			case "NON":
			PreparedStatement assignSlot;
			try {
				assignSlot = con.prepareStatement("insert into slot values (?,?)");
				assignSlot.setInt(1, schedule);
				assignSlot.setInt(2, crewId);
//				if (!assignSlot.execute()) {
//					PreparedStatement updateAvailablity = con.prepareStatement(
//							"update crew_availability set status=? where crew_id =? and date=?");
//					updateAvailablity.setString(1, "NOT");
//					updateAvailablity.setInt(2, crewId);
//					updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//					if (!updateAvailablity.execute()) {
//						System.out.println("Slot has been assigned");
//					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				break;
			case "NOR":
			try {
				PreparedStatement assignCrew = con.prepareStatement("Insert into slot values (?,?)");
				assignCrew.setInt(1, schedule);
				assignCrew.setInt(2, crewId);
				if(!assignCrew.execute()) {
					System.out.println("Slot assigned");
				}		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				
				break;
		}
	}
	public void denySlot(int schedule,int crewId,String type) {
		Database db = new Database();
		Connection con = db.getConnection();
		switch(type) {
		case "COMP": 
//			PreparedStatement updateAvailablity = con
//			.prepareStatement("update crew_availability set status=? where crew_id =? and date=?");
//	updateAvailablity.setString(1, "NA");
//	updateAvailablity.setInt(2, crewId);
//	updateAvailablity.setDate(3, new java.sql.Date(date.getTime()));
//	if (!updateAvailablity.execute()) {
//		PreparedStatement increaseLeave = con
//				.prepareStatement("update crew set leave_count+=? where id=? ");
//		increaseLeave.setInt(1, 1);
//		increaseLeave.setInt(2, crew_id);
//		if (!increaseLeave.execute())
//			System.out.println("Slot has not been assigned");
//	}
			break;
		case "NOR":
			PreparedStatement updateAvailablity;
			try {
				updateAvailablity = con	.prepareStatement("update slot_requests set status=? where crew_id =? and flight_schedule_no=?");
				updateAvailablity.setString(1, "REJ");
				updateAvailablity.setInt(2, crewId);
				updateAvailablity.setInt(3, schedule);
				if(!updateAvailablity.execute()) {
					System.out.println("Slot not assigned!");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			
			break;
		
		}
	}
}
