package coda.global.airport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import coda.global.bean.Customer;
import coda.global.utility.Database;

public class CustomerLoginRegister {
	Scanner scan = new Scanner(System.in);
	Customer customer = new Customer();
	
	public void register(HttpServletRequest request) {
		Database db = new Database();
		Connection con = db.getConnection();				
		customer.setName(request.getParameter("name"));		
		customer.setGender(request.getParameter("gender"));		
		customer.setContactNo(Integer.parseInt(request.getParameter("contactNumber")));		
		customer.setPassword(request.getParameter("password"));
		System.out.println("dndnj");
		try {
			PreparedStatement insertCustomer = con.prepareStatement("insert into customer (contact_number,name,gender,password) values(?,?,?,?)");
			insertCustomer.setInt(1, customer.getContactNo());
			insertCustomer.setString(2, customer.getName());
			insertCustomer.setString(3,customer.getGender());
			insertCustomer.setString(4, customer.getPassword());
			if(insertCustomer.execute()) {
				System.out.println("Youre registered");
			}else {
				System.out.println("Not registered");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Customer login(int id,String password) {
		Customer customer = new Customer();
		Database db = new Database();
		Connection con = db.getConnection();	
				 
		try {
			PreparedStatement loginCustomer = con.prepareStatement("select * from customer where id = ? and password =?");
			loginCustomer.setInt(1, id);
			loginCustomer.setString(2, password);
			ResultSet cust = loginCustomer.executeQuery();			
			if(cust.wasNull()) {
				System.out.println("Enter a proper email or password");
			}
			while(cust.next()) {
				customer.setCustomerId(cust.getInt(1));
				customer.setName(cust.getString(2));
				customer.setGender(cust.getString(3));
				customer.setContactNo(cust.getInt(4));
				customer.setPassword(cust.getString(5));
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer=null;
		
	}
}
