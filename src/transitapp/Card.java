package transitapp;

import java.time.LocalDateTime;

public class Card {
	private int id;
	private double balance;
	private boolean accountOpen;
	private boolean inTransit;
	private LocalDateTime beginTransitTime;
	private int beginTransitLocation;
	private String[] recentTrips = new String[3];
	
	public Card(int id, double startingBalance) {
		this.id = id;
		this.balance = startingBalance;
		this.accountOpen = true;
		this.inTransit = false;
		this.beginTransitTime = LocalDateTime.now();
		this.beginTransitLocation = -1;
	}
	
	public Card(int id, double bal, boolean accountOpen, boolean inTransit, LocalDateTime beginTime, int beginLocation, String[] recentTrips) {
		this.id = id;
		this.balance = bal;
		this.accountOpen = accountOpen;
		this.inTransit = inTransit; 
		this.beginTransitTime = beginTime;
		this.beginTransitLocation = beginLocation;
		this.recentTrips = recentTrips;
	}
	
	public int getId() {
		// get card id
		return this.id;
	}
	public double getBalance() {
		// get card balance
		return this.balance;
	}
	public LocalDateTime getBeginTransitTime() {
		// get time when the trip began
		return this.beginTransitTime;
	}
	public int getBeginTransitLocation() {
		// get location that the trip began at
		return this.beginTransitLocation;
	}
	
	public boolean isAccountOpen() {
		return this.accountOpen;
	}
	public boolean isInTransit() {
		return this.inTransit;
	}
	
	public void rechargeBalance(double charge) {
		// adds charge to card balance
		this.balance += charge;
	}
	public void openAccount() {
		// opens this account
		this.accountOpen = true;
	}
	public void closeAccount() {
		// closes this account
		this.accountOpen = false;
	}
	public void beginTransit(int locationCount, LocalDateTime currentTime) {
		// begins a trip with this card
		this.inTransit = true;
		this.beginTransitTime = currentTime;
		this.beginTransitLocation = locationCount;
	}
	public void endTransit(double tripCost) {
		// ends a trip with this card
		this.inTransit = false;
		this.balance -= tripCost;
	}
	public void addRecentTrip(String tripInformation) {
		int i = 0;
		while (i<recentTrips.length) {
			if (recentTrips[i] == null) {
				recentTrips[i] = tripInformation;
				return;
			}
			i++;
		}
		recentTrips[0] = recentTrips[1];
		recentTrips[1] = recentTrips[2];
		recentTrips[2] = tripInformation;
	}
	public String[] getRecentTrips() {
		return this.recentTrips;
	}
	
}
