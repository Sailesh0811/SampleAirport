package coda.global.airport;

import coda.global.bean.Crew;

public interface CrewInterface {

	void start();

	String viewProfile(Crew crew);

	String[] assignedDuty(Crew crew);

	String slotRequest(Crew crew,String d,String destination);

	String leaveRequest(Crew crew,String d,String[] cd,int n);

}