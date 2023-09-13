package transitapp;

public class Bus extends Vehicle {
	private static float FARE_COST;
	
	public Bus(String name, String[] route, TransitSystem ts) {
		super(name, route, ts);
		this.setFARE_COST(2.0f);
	}
	public void tapCardTerminal(Card card) {
		Bus.transitSystem.tapCard(this, this.getCurrentLocation(), card);
	}
	public void setFARE_COST(float fare) {
		Bus.FARE_COST = fare;
	}
	public float getFARE_COST() {
		return Bus.FARE_COST;
	}
	
}
