package coda.global.bean;

public class Transaction {
	private int pnrNo;
	public int getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(int pnrNo) {
		this.pnrNo = pnrNo;
	}
	public int getAvailableFlightNo() {
		return availableFlightNo;
	}
	public void setAvailableFlightNo(int availableFlightNo) {
		this.availableFlightNo = availableFlightNo;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	private int customerId;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	private int availableFlightNo;
	private int contactNo;
	private int seats;
	String flight_class;
	String status;
	float price;
	public String getFlight_class() {
		return flight_class;
	}
	public void setFlight_class(String flight_class) {
		this.flight_class = flight_class;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
}
