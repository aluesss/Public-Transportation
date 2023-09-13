package transitapp;

public class VehicleFactory {
	public Vehicle createVehicle(String product, String name, String[] route, TransitSystem ts) {
		Vehicle vehicle = null;
		if(product.equalsIgnoreCase("bus")) {
			vehicle = new Bus(name, route, ts);
		}
		else if (product.equalsIgnoreCase("metro")) {
			vehicle = new Metro(name, route, ts);
		}
		return vehicle;
	}
}
