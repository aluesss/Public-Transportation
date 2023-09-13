package transitapp;

import java.util.*;
import java.io.*;

public class Admin {

	public static float COST_PER_STATION = 0.1f;

	private String username;
	private String password;

	private TransitSystem transit;

	public Admin(String username, String password, TransitSystem transit) {
		this.username = username;
		this.password = password;
		this.transit = transit;
	}

	public void openSystem() {
		this.transit.openSystem();
	}

	public void closeSystem() {
		this.transit.closeSystem();
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	public Bus createBus(String name, String[] route) {
		VehicleFactory vF = new VehicleFactory();
		Bus v = (Bus) vF.createVehicle("bus", name, route, transit);
		System.out.println("Bus " + v.getName() + " has been successfully created with the given parameters.");
		transit.getBuses().put(v.getName(), (Bus) v);
		return v;
	}

	public Metro createMetro(String name, String[] route) {
		VehicleFactory vF = new VehicleFactory();
		Metro v = (Metro) vF.createVehicle("metro", name, route, transit);
		System.out.println("Metro " + v.getName() + " has been successfully created with the given parameters.");
		transit.getMetro().put(v.getName(), (Metro) v);
		return v;
	}

	public void removeBus(String name) {
		transit.getBuses().remove(name);
	}

	public void removeMetro(String name) {
		transit.getMetro().remove(name);
	}

	public double getRevenue() {
		double totalRevenue = 0.0;
		for (Vehicle bus : transit.getBuses().values()) {
			totalRevenue += bus.getRevenue();
		}
		for (Vehicle m : transit.getMetro().values()) {
			totalRevenue += m.getRevenue();
		}
		return totalRevenue;

	}

	public double getOperationalCost() {
		int totalLocationReached = 0;
		for (Vehicle bus : transit.getBuses().values()) {
			totalLocationReached += bus.getLocationsReached();
		}
		for (Vehicle m : transit.getMetro().values()) {
			totalLocationReached += m.getLocationsReached();
		}
		return totalLocationReached * COST_PER_STATION;

	}

	public ArrayList<String> viewUsers() {
		ArrayList<String> userInformation = new ArrayList<>();

		for (CardHolder cH : transit.getCH().values()) {
			String name = cH.getName();
			String email = cH.getEmail();
			String cards = cH.getCardInfo();
			userInformation.add(name + "|" + email + "|" + "\n" + cards);
		}

		return userInformation;
	}
}
