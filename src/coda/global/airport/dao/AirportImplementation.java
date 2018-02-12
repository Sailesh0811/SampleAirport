package coda.global.airport.dao;

import java.util.Scanner;

public class AirportImplementation {
	public static void main(String args[]) {
		char choice = 'Y';
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Are you a 1.Customer 2.Crew Member 3.Admin ? or 4.Exit");
			switch (scan.nextInt()) {
			case 1:
				new CustomerImplementation();
				break;
			case 2:
				new CrewImplementation();
				break;
			case 3: new AdminImplementation();
				break;
			case 4:
				choice = 'N';
				break;
			default:
				System.out.println("Enter a proper choice");
			}
		} while (choice == 'Y');
		scan.close();
	}
}
