package coda.global.airport.delegates;

import java.sql.SQLException;
import java.text.ParseException;

import coda.global.airport.dao.AdminImplementation;
import coda.global.airport.dao.CrewRegistrationLogin;
import coda.global.bean.Crew;
import coda.global.bean.Flight;

public class AdminDelegate {
	AdminImplementation adminImplementation = new AdminImplementation();
	public String register(Crew crew,String[] languages) {
		CrewRegistrationLogin crewRegistrationLogin = new CrewRegistrationLogin();
		return crewRegistrationLogin.register(crew, languages);
	}
	public String addFlight(Flight flight) {
		String result ="";
		try {
			result = adminImplementation.addFlight(flight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String addSeats(Flight flight) {
		String result ="";
		try {
			result = adminImplementation.addSeats(flight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public String scheduleFlight(Flight flight, String atime, String dtime, String dat) {
		String result ="";
		try {
			result = adminImplementation.scheduleFlight(flight, atime, dtime, dat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
