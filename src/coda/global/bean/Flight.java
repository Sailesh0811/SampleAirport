package coda.global.bean;

import java.sql.Time;
import java.util.Date;

public class Flight {
		private int id;
		private String flightNo;
		private String airline;
		private String from;
		private String to;
		private int availableFlightNo;
		private Date date;
		private Date arrival;
		private Date departure;
		private float economyFare;
		private float businessFare;
		private int seats;
		private String cls;
		private int economySeats;
		private int businessSeats;
		private int startEconomySeatNo;
		private int startBusinessSeatNo;
		private int endEconomySeatNo;
		private int endBusinessSeatNo;
		private float price;
		public int getStartEconomySeatNo() {
			return startEconomySeatNo;
		}
		public void setStartEconomySeatNo(int startEconomySeatNo) {
			this.startEconomySeatNo = startEconomySeatNo;
		}
		public int getStartBusinessSeatNo() {
			return startBusinessSeatNo;
		}
		public void setStartBusinessSeatNo(int startBusinessSeatNo) {
			this.startBusinessSeatNo = startBusinessSeatNo;
		}
		public int getEndEconomySeatNo() {
			return endEconomySeatNo;
		}
		public void setEndEconomySeatNo(int endEconomySeatNo) {
			this.endEconomySeatNo = endEconomySeatNo;
		}
		public int getEndBusinessSeatNo() {
			return endBusinessSeatNo;
		}
		public void setEndBusinessSeatNo(int endBusinessSeatNo) {
			this.endBusinessSeatNo = endBusinessSeatNo;
		}
		public String getCls() {
			return cls;
		}
		public void setCls(String cls) {
			this.cls = cls;
		}
		public int getSeats() {
			return seats;
		}
		public void setSeats(int seats) {
			this.seats = seats;
		}
		public float getEconomyFare() {
			return economyFare;
		}
		public void setEconomyFare(float economyFare) {
			this.economyFare = economyFare;
		}
		public float getBusinessFare() {
			return businessFare;
		}
		public void setBusinessFare(float businessFare) {
			this.businessFare = businessFare;
		}
		public String getFlightNo() {
			return flightNo;
		}
		public void setFlightNo(String flightNo) {
			this.flightNo = flightNo;
		}
		public String getAirline() {
			return airline;
		}
		public void setAirline(String airline) {
			this.airline = airline;
		}
		public String getFrom() {
			return from;
		}
		public void setFrom(String from) {
			this.from = from;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public int getAvailableFlightNo() {
			return availableFlightNo;
		}
		public void setAvailableFlightNo(int availableFlightNo) {
			this.availableFlightNo = availableFlightNo;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public Date getArrival() {
			return arrival;
		}
		public void setArrival(Date arrival) {
			this.arrival = arrival;
		}
		public Date getDeparture() {
			return departure;
		}
		public void setDeparture(Date departure) {
			this.departure = departure;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getEconomySeats() {
			return economySeats;
		}
		public void setEconomySeats(int economySeats) {
			this.economySeats = economySeats;
		}
		public int getBusinessSeats() {
			return businessSeats;
		}
		public void setBusinessSeats(int businessSeats) {
			this.businessSeats = businessSeats;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
}
