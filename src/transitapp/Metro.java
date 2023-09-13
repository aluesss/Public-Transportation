package transitapp;

public class Metro extends Vehicle{
	private static float FARE_COST;
	
	public Metro(String name, String[] route, TransitSystem ts) {
		super(name, route, ts);
		this.setFARE_COST(0.5f);
	}
	public void tapCardTerminal(Card card) {
		Metro.transitSystem.tapCard(this, this.getCurrentLocation(), card);
	}
	public void setFARE_COST(float fare) {
		Metro.FARE_COST = fare;
	}
	public float getFARE_COST() {
		return Metro.FARE_COST;
	}
}
