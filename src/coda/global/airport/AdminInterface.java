package coda.global.airport;

import java.util.List;

import coda.global.bean.Crew;
import coda.global.bean.Flight;
import coda.global.bean.Leave;

public interface AdminInterface {

	void addCrewMember();

	String addFlight(Flight flight);

	String addSeats(Flight flight);

	String scheduleFlight(Flight flight,String atime,String dtime,String date);

	List<Leave> leaveApproval();

	void viewAddedFlight();

	void viewScheduledFlight();

	List<Crew> slotAssign(int scheduleFlight);

}