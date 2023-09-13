package transitapp;

public class Vehicle {
	private String name;
	private String[] route;
	private double revenue = 0.0;
	private int locationsReached = 0;
	private int currentLocation = 0;
	public static TransitSystem transitSystem;

	public Vehicle(String name, String[] route, TransitSystem ts) {
		this.name = name;
		this.route = route;
		Vehicle.transitSystem = ts;
	}

	public void addRevenue(double revenue) {
		this.revenue += revenue;
	}

	public double getRevenue() {
		return this.revenue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getRoute() {
		return route;
	}

	public void setRoute(String[] route) {
		this.route = route;
	}

	public int getCurrentLocation() {
		return this.currentLocation;
	}

	public void setCurrentLocation(int loc) {
		this.currentLocation = loc;
	}

	public void addLocationsReached(int num) {
		this.locationsReached += num;
	}

	public int getLocationsReached() {
		return this.locationsReached;
	}

	public void moveVehicle() {
		if (this.currentLocation + 1 < route.length) {
			this.currentLocation += 1;
			System.out.println(this.name + " has reached " + this.route[this.getCurrentLocation()]);
		} else {
			System.out.println(this.name + " has already reach the end of its route.");
		}
	}
}
