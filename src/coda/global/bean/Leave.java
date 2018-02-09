package coda.global.bean;

import java.util.Date;

public class Leave {
	private int id;
	private int crewId;
	private Date date;
	private int noOfDays;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCrewId() {
		return crewId;
	}
	public void setCrewId(int crewId) {
		this.crewId = crewId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
