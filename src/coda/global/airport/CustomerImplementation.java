package coda.global.airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import coda.global.bean.Customer;
import coda.global.bean.Flight;
import coda.global.bean.Transaction;
import coda.global.utility.Database;

public class CustomerImplementation implements CustomerInterface {
	// Customer customer = new Customer();
	Scanner scan = new Scanner(System.in);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat tf = new SimpleDateFormat("hh:mm");
	Database db = new Database();
	Connection con;
	Random rand = new Random();
	int pnrNo = rand.nextInt(100) + 10000;

	public CustomerImplementation() {
		con = db.getConnection();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#start()
	 */

	@Override
	public List<Flight> searchFlight(HttpServletRequest request, String filter) {
		Flight flight = new Flight();
		List<Flight> flightList = new LinkedList<Flight>();
		System.out.println(request.getParameter("from"));
		flight.setFrom(request.getParameter("from"));
		flight.setTo(request.getParameter("to"));
		Date date;
		try {
			date = df.parse(request.getParameter("date"));
			flight.setDate(date);

		} catch (ParseException e) {
			System.out.println("Enter a proper date ");
		}

		flight.setSeats(Integer.parseInt(request.getParameter("seats")));
		flight.setCls(request.getParameter("class"));
		System.out.println(filter);
		flightList = searchWithFilter(flight, filter);
		
		return flightList;

		/* Searching for flight */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#searchWithFilter(coda.global.bean.
	 * Flight, java.lang.String)
	 */
	@Override
	public List<Flight> searchWithFilter(Flight flight, String filter) {
		HashSet<Integer> flightSet = new HashSet<Integer>();
		List<Flight> flightList = new LinkedList<Flight>();
		String avail = "";
		try {
			PreparedStatement searchStatement;
			String searchQuery = "";
			switch (filter) {
			case "LOWRATE":
				if (flight.getCls().equalsIgnoreCase("EC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.economy_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.economy_seats >= ? order by fs.economy_fare";
				else if (flight.getCls().equalsIgnoreCase("BC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.business_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? =?and f.id = fs.flight_id and fs.date = ? and fs.business_seats >= ? order by fs.business_fare ";
				break;
			case "HIGHRATE":
				if (flight.getCls().equalsIgnoreCase("EC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.economy_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.economy_seats >= ? order by fs.economy_fare desc";
				else if (flight.getCls().equalsIgnoreCase("BC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.business_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.business_seats >= ? order by fs.business_fare desc ";
				break;
			case "AIRLINE":
				System.out.println("Enter the airline name");
				String airline = scan.next();
				if (flight.getCls().equalsIgnoreCase("EC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.economy_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.economy_seats >= ? and airline="
							+ airline + "  order by fs.economy_fare desc";
				else if (flight.getCls().equalsIgnoreCase("BC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.business_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.business_seats >= ? and airline="
							+ airline + " order by fs.business_fare desc ";
				break;
			case "LDEPARTURE":
				if (flight.getCls().equalsIgnoreCase("EC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.economy_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.economy_seats >= ?   order by fs.departure ";
				else if (flight.getCls().equalsIgnoreCase("BC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.business_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.business_seats >= ? order by fs.departure ";
				break;
			case "HDEPARTURE":
				if (flight.getCls().equalsIgnoreCase("EC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.economy_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.economy_seats >= ?   order by fs.departure desc ";
				else if (flight.getCls().equalsIgnoreCase("BC"))
					searchQuery = "select f.flight_no,f.airline,fs.departure,fs.arrival,fs.business_fare,fs.flight_schedule_no from flight f inner join flight_schedule fs on f.boarding_place = ? and f.destination =? and f.id = fs.flight_id and fs.date = ? and fs.business_seats >= ? order by fs.departure desc ";
				break;
			}

			searchStatement = con.prepareStatement(searchQuery);
			searchStatement.setString(1, flight.getFrom());
			searchStatement.setString(2, flight.getTo());
			searchStatement.setDate(3, new java.sql.Date(flight.getDate().getTime()));
			searchStatement.setInt(4, flight.getSeats());
			ResultSet searchResult = searchStatement.executeQuery();
			boolean flightFoundFlag = false;
			while (searchResult.next()) {
				flightFoundFlag = true;
				Flight flightSearch = new Flight();
				flightSearch.setFlightNo(searchResult.getString(1));
				flightSearch.setAirline(searchResult.getString(2));
				flightSearch.setDeparture(searchResult.getTime(3));
				flightSearch.setArrival(searchResult.getTime(4));

				if (flight.getCls().equalsIgnoreCase("EC")) {
					flightSearch.setEconomyFare(searchResult.getFloat(5));
					flightSearch.setPrice(searchResult.getFloat(5)*flight.getSeats());
				}
				else {
					flightSearch.setBusinessFare(searchResult.getFloat(5));
				flightSearch.setPrice(searchResult.getFloat(5)*flight.getSeats());
				}
				flightSearch.setAvailableFlightNo(searchResult.getInt(6));
				flightSet.add(flightSearch.getAvailableFlightNo());
				System.out.println("cdaad");
				flightList.add(flightSearch);
			}
			return flightList;
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flightList=null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * coda.global.airport.CustomerInterface#filterSearch(coda.global.bean.Flight)
	 */
	@Override
	public void filterSearch(Flight flight) {
		char choice = 'Y';
		System.out.println(
				"1.Low rate filter 2.High Rate Filter 3.Airline filter 4.Early departure filter 5.Late departure filter 6.Exit");
		switch (scan.nextInt()) {
		case 1:
			searchWithFilter(flight, "LOWRATE");
			break;
		case 2:
			searchWithFilter(flight, "HIGHRATE");
			break;
		case 3:
			searchWithFilter(flight, "AIRLINE");
			break;
		case 4:
			searchWithFilter(flight, "LDEPARTURE");
			break;
		case 5:
			searchWithFilter(flight, "HDEPARTURE");
			break;
		case 6:
			choice = 'N';
			break;
		default:
			System.out.println("Enter a proper choice");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#book(coda.global.bean.Flight)
	 */
	@Override
	public int book(Flight flight, Customer customer, String[] passenger) {
		CustomerLoginRegister cust = new CustomerLoginRegister();
		if (customer.getName() != null) {
			Transaction transaction = new Transaction();
			System.out.println("Welcome " + customer.getName());

			System.out.println(flight.getAvailableFlightNo());
			transaction.setPnrNo(pnrNo);
			transaction.setCustomerId(customer.getCustomerId());
			transaction.setContactNo(customer.getContactNo());
			transaction.setSeats(flight.getSeats());
			transaction.setFlight_class(flight.getCls());

			try {
				PreparedStatement flightNoSearch;
				if (flight.getCls().equalsIgnoreCase("EC"))
					flightNoSearch = con.prepareStatement(
							"Select economy_fare from flight_schedule where flight_schedule_no=? and date=?");
				else
					flightNoSearch = con.prepareStatement(
							"Select busniess_fare from flight_schedule where flight_schedule_no=? and date=?");
				flightNoSearch.setInt(1, flight.getAvailableFlightNo());
				flightNoSearch.setDate(2, new java.sql.Date(flight.getDate().getTime()));
				ResultSet rs = flightNoSearch.executeQuery();
				rs.next();
				transaction.setAvailableFlightNo(flight.getAvailableFlightNo());
				if (flight.getCls().equalsIgnoreCase("EC")) {
					transaction.setPrice(rs.getFloat(1) * flight.getSeats());
				} else {
					transaction.setPrice(rs.getFloat(1) * flight.getSeats());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				PreparedStatement insertTransaction = con
						.prepareStatement("insert into transaction values (?,?,?,?,?,?,?)");
				insertTransaction.setInt(1, transaction.getPnrNo());
				insertTransaction.setInt(2, transaction.getCustomerId());
				insertTransaction.setInt(3, transaction.getAvailableFlightNo());
				insertTransaction.setString(4, transaction.getFlight_class());
				insertTransaction.setInt(5, transaction.getSeats());
				insertTransaction.setString(6, "CNF");
				insertTransaction.setFloat(7, transaction.getPrice());
				if (!insertTransaction.execute()) {
					for (int i = 0; i < flight.getSeats(); i++) {
						PreparedStatement insertPassenger = con
								.prepareStatement("insert into passenger (pnr,passenger_name) values (?,?)");
						insertPassenger.setInt(1, transaction.getPnrNo());
						insertPassenger.setString(2, passenger[i]);
						if (!insertPassenger.execute()) {
							PreparedStatement reduceSeat;
							if (flight.getCls().equalsIgnoreCase("EC")) {
								reduceSeat = con.prepareStatement(
										"Update flight_schedule set economy_seats = economy_seats-? where flight_schedule_no=?");
								reduceSeat.setInt(1, 1);
								reduceSeat.setInt(2, transaction.getAvailableFlightNo());

							} else {
								reduceSeat = con.prepareStatement(
										"Update flight_schedule set economy_seats = business_seats-? where flight_schedule_no=?");

								reduceSeat.setInt(1, 1);
								reduceSeat.setInt(2, transaction.getAvailableFlightNo());
							}
							if (!reduceSeat.execute()) {
								System.out.println("Your ticket is bookedssss");
							}
						}

					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("your tickets are booked");

		}
		return pnrNo++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#viewHistory()
	 */
	@Override
	public List<Transaction> viewHistory(Customer customer, String choice, int pnr) {
		List<Transaction> transactionList=new LinkedList<Transaction>();
		
		if (customer.getName() != null) {
			switch (choice) {
			case "1":
				boolean found = true;

				System.out.println("Enter the pnr no");
				int pnrNo = pnr;
				try {
					PreparedStatement pnrSearch = con.prepareStatement(
							"select t.pnr,t.status,t.flight_schedule_no,p.passenger_name,p.seat_no from transaction t inner join passenger p on  t.pnr=p.pnr  where t.pnr=? and t.customer_id=?  ");
					pnrSearch.setInt(1, pnrNo);
					pnrSearch.setInt(2, customer.getCustomerId());
					ResultSet pnrResultSet = pnrSearch.executeQuery();
					while (pnrResultSet.next()) {
						Transaction transaction = new Transaction();
						transaction.setPnrNo(pnrResultSet.getInt(1));
						transaction.setStatus(pnrResultSet.getString(2));
						transaction.setAvailableFlightNo(pnrResultSet.getInt(3));
						found = false;
						transactionList.add(transaction);
					}
					if (found)
						transactionList=null;
				} catch (SQLException e) {

					e.printStackTrace();
				}
				break;
			case "2":
				found = true;
				try {
					PreparedStatement pnrSearch = con.prepareStatement(
							"select t.pnr,t.status,t.flight_schedule_no,p.passenger_name,p.seat_no from transaction t , passenger p  where t.pnr=p.pnr and t.customer_id = ?  ");
					pnrSearch.setInt(1, customer.getCustomerId());
					ResultSet pnrResultSet = pnrSearch.executeQuery();
					while (pnrResultSet.next()) {
						Transaction transaction=new Transaction();
						transaction.setPnrNo(pnrResultSet.getInt(1));
						transaction.setStatus(pnrResultSet.getString(2));
						transaction.setAvailableFlightNo(pnrResultSet.getInt(3));
						found = false;
						transactionList.add(transaction);
						System.out.println(transaction.getPnrNo());
					}
					if(found) {
						//transactionList=null;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Enter a proper choice");
			}
		}
		
		return transactionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#cancelTicket()
	 */
	@Override
	public String cancelTicket(Customer customer, int pnr) {

		int pnrNo = pnr;
		String result = "";
		PreparedStatement cancelStatement;
		boolean found = true;
		try {
			cancelStatement = con.prepareStatement("Select * from transaction where pnr = ? and status ='CNF'");
			cancelStatement.setInt(1, pnrNo);
			ResultSet cancelResult = cancelStatement.executeQuery();
			Transaction transaction = new Transaction();
			while (cancelResult.next()) {

				transaction.setPnrNo(pnrNo);
				transaction.setCustomerId(cancelResult.getInt(2));
				transaction.setAvailableFlightNo(cancelResult.getInt(3));
				transaction.setFlight_class(cancelResult.getString(4));
				transaction.setSeats(cancelResult.getInt(5));
				transaction.setStatus(cancelResult.getString(6));
				transaction.setPrice(cancelResult.getFloat(7));
				found = false;
			}
			if (!found) {
				PreparedStatement increaseSeat, updateStatus;
				if (transaction.getFlight_class().equalsIgnoreCase("EC")) {
					increaseSeat = con.prepareStatement(
							"Update flight_schedule set economy_seats = economy_seats+? where flight_schedule_no=?");
					increaseSeat.setInt(1, transaction.getSeats());
					increaseSeat.setInt(2, transaction.getAvailableFlightNo());
				} else {
					increaseSeat = con.prepareStatement(
							"Update flight_schedule set business_seats = business_seats+? where flight_schedule_no=?");
					increaseSeat.setInt(1, transaction.getSeats());
					increaseSeat.setInt(2, transaction.getAvailableFlightNo());
				}
				if (!increaseSeat.execute()) {
					updateStatus = con.prepareStatement("update transaction set status = ? where pnr=?");
					updateStatus.setString(1, "CAN");
					updateStatus.setInt(2, transaction.getPnrNo());
					if (!updateStatus.execute()) {
						result += ("Your ticket is cancelled");
						result += ("your refund of  " + transaction.getPrice() + " will be done soon");

					}

				}
			} else {
				result += ("Enter a proper pnr no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coda.global.airport.CustomerInterface#checkIn()
	 */
	@Override
	public String[] checkIn(Customer customer, int pnr) {
		String[] seats = new String[3];
		seats[1]="";
		int pnrNo = pnr;
		boolean found = true;
		Transaction transaction = new Transaction();
		try {
			PreparedStatement scanTransaction = con
					.prepareStatement("select * from transaction where pnr = ? and status ='CNF'");
			scanTransaction.setInt(1, pnrNo);
			ResultSet rs = scanTransaction.executeQuery();
			while (rs.next()) {
				transaction.setPnrNo(pnrNo);
				transaction.setCustomerId(rs.getInt(2));
				transaction.setAvailableFlightNo(rs.getInt(3));
				transaction.setFlight_class(rs.getString(4));
				transaction.setSeats(rs.getInt(5));
				seats[0]=""+transaction.getSeats();
				seats[2]=""+transaction.getPnrNo();
				transaction.setStatus(rs.getString(6));
				transaction.setPrice(rs.getFloat(7));
				found = false;
			}
			if (!found) {
				PreparedStatement scheduledFlighIt = con
						.prepareStatement("select flight_id from flight_schedule where flight_schedule_no =?");
				scheduledFlighIt.setInt(1, transaction.getAvailableFlightNo());
				ResultSet scheduledFlightSet = scheduledFlighIt.executeQuery();
				int startSeatNo = 1, endingSeatNo = 15;
				while (scheduledFlightSet.next()) {
					PreparedStatement seatStatement = con.prepareStatement(
							"select start_seat_no,ending_seat_no from seats where flight_id =? and flight_class=?");
					seatStatement.setInt(1, scheduledFlightSet.getInt(1));
					seatStatement.setString(2, transaction.getFlight_class());
					ResultSet seatSet = seatStatement.executeQuery();
					while (seatSet.next()) {
						startSeatNo = seatSet.getInt(1);
						endingSeatNo = seatSet.getInt(2);
					}

				}
				PreparedStatement findBookedPnr = con
						.prepareStatement("select pnr from transaction where flight_schedule_no=?");
				findBookedPnr.setInt(1, transaction.getAvailableFlightNo());
				ResultSet bookedPnr = findBookedPnr.executeQuery();
				HashSet<Integer> bookedPnrSet = new HashSet<Integer>();
				while (bookedPnr.next()) {
					bookedPnrSet.add(bookedPnr.getInt(1));
					System.out.println("booked pnr" + bookedPnr.getInt(1));
				}
				boolean alloted = true;

				for (int j = 0; j < transaction.getSeats(); j++) {
					for (int i = startSeatNo; i <= endingSeatNo; i++) {
						alloted = true;

						PreparedStatement findBookedSeats = con
								.prepareStatement("select * from passenger where seat_no =?");
						findBookedSeats.setString(1, "" + i);
						ResultSet seatsSet = findBookedSeats.executeQuery();
						while (seatsSet.next()) {
							if (bookedPnrSet.contains(seatsSet.getInt(1))) {
								alloted = false;
							}
						}
						if (alloted) {
							if (i % 2 == 1) {
								seats[1] += ("W:" + i + ",");
							} else {
								seats[1] += ("N:" + i + ",");
							}
						}

					}

					
				}
			} else {
				seats[1]+=("error");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seats;
	}
	
	
	public String addSeats(String[] seats,int pnr) {
		String result="";
		
		 for(int i=0;i<seats.length;i++) {
		 int seatSelected =Integer.parseInt(seats[i]);
		 PreparedStatement updateStatus;
		 try {
			updateStatus = con.prepareStatement("update passenger set seat_no=? where pnr=? and seat_no ='NA' limit 1");
			updateStatus.setString(1, seatSelected+"");
			 updateStatus.setInt(2,pnr );
			 updateStatus.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 PreparedStatement statusUpadate;
		try {
			statusUpadate = con.prepareStatement("update transaction set status =? where pnr =?");
			 statusUpadate.setString(1, "CHK");
			 statusUpadate.setInt(2, pnr);
			 if(!statusUpadate.execute()) {
			result+=("Checked in");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
		 return result;
	}
	public String[] cityFromList() {
		Connection con = new Database().getConnection();
		String[] from;String temp="";
		try {
			PreparedStatement ps = con.prepareStatement("select DISTINCT boarding_place from flight");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp+=rs.getString(1)+",";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return from=temp.split(",");
		
		
	}
	public String[] cityToList() {
		Connection con = new Database().getConnection();
		String[] to;String temp="";
		try {
			PreparedStatement ps = con.prepareStatement("select DISTINCT destination from flight");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp+=rs.getString(1)+",";
				System.out.println(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return to=temp.split(",");
		
	}
}
