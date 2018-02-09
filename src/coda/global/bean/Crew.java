package coda.global.bean;

public class Crew extends Person{
	private int contactNo;
	private int crewId;
	private String designation;
	private int leaveDays;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public int getCrewId() {
		return crewId;
	}
	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}
}
