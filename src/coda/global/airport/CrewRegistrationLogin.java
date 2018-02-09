package coda.global.airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import coda.global.bean.Crew;
import coda.global.utility.Database;

public class CrewRegistrationLogin {

	
	public String register(Crew crew,String [] languages) {
		Database db = new Database();
		Connection con = db.getConnection();
		String result="";
		try {
			PreparedStatement insertcrew = con.prepareStatement("insert into crew (contact_no,crew_name,gender,password,designation) values(?,?,?,?,?)");
			insertcrew.setInt(1, crew.getContactNo());
			insertcrew.setString(2, crew.getName());
			insertcrew.setString(3,crew.getGender());
			insertcrew.setString(4, crew.getPassword());
			insertcrew.setString(5, crew.getDesignation());
			if(!insertcrew.execute()) {
				result+=("Crew registered");
			}else {
				result+=("Not registered");
			}
		
		int n = languages.length;
		for(int i=0;i<n;i++) {
			PreparedStatement insertLanguages = con.prepareStatement("insert into (?,?)");
			insertLanguages.setInt(1, crew.getCrewId());
			
			insertLanguages.setString(2, languages[i]);
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Crew login(int id,String password) {
		Crew crew = new Crew();
		Database db = new Database();
		Connection con = db.getConnection();		
		int mobileNumber = id;	
		
		try {
			PreparedStatement logincrew = con.prepareStatement("select id,crew_name,gender,contact_no,password,designation,leave_count from crew where id = ? and password =?");
			logincrew.setInt(1, mobileNumber);
			logincrew.setString(2, password);
			ResultSet cust = logincrew.executeQuery();
			
			if(cust.wasNull()) {
				return crew=null;
			}
			while(cust.next()) {
				crew.setCrewId(cust.getInt(1));
				crew.setName(cust.getString(2));
				crew.setGender(cust.getString(3));
				crew.setContactNo(cust.getInt(4));
				crew.setPassword(cust.getString(5));
				crew.setDesignation(cust.getString(6));
				crew.setLeaveDays(cust.getInt(7));
				return crew;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return crew=null;
	}
}
