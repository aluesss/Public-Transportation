package transitapp;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TransitSystem {

	private boolean transitSystemOpen = false;

	private static int id = 10000001;
	private static float COST_PER_STATION = 0.1f;
	private static float BUS_FARE = 2.0f;
	private static float METRO_FARE = 0.5f;

	private File initFile;
	private File adminLog;
	private File userLog;
	private File transitLog;
	private File busLog;
	private File metroLog;

	private HashMap<String, Admin> adminUsers = new HashMap<>();
	private HashMap<String, CardHolder> cHUsers = new HashMap<>();

	private HashMap<String, Bus> buses = new HashMap<>();
	private HashMap<String, Metro> metro = new HashMap<>();

	public TransitSystem() {
		File logs = new File(System.getProperty("user.dir") + "/logs");
		logs.mkdirs();
		initFile = new File(logs, "/initFile.txt");
		adminLog = new File(logs, "/adminLog.txt");
		userLog = new File(logs, "/userLog.txt");
		transitLog = new File(logs, "/transitLog.txt");
		busLog = new File(logs, "/busLog.txt");
		metroLog = new File(logs, "/metroLog.txt");
		if (!initFile.exists()) {
			try {
				initFile.createNewFile();
				BufferedWriter bW = new BufferedWriter(new FileWriter(initFile));
				bW.write("id=100000001\n");
				bW.write("COST_PER_STATION=0.1\n");
				bW.write("BUS_FARE=2.0\n");
				bW.write("METRO_FARE=0.5\n");
				bW.flush();
				bW.close();
			} catch (IOException e) {
			}
		}
		if (!adminLog.exists()) {
			try {
				adminLog.createNewFile();
			} catch (IOException e) {
			}
		}
		if (!userLog.exists()) {
			try {
				userLog.createNewFile();
			} catch (IOException e) {
			}
		}
		if (!transitLog.exists()) {
			try {
				transitLog.createNewFile();
			} catch (IOException e) {
			}
		}
		if (!busLog.exists()) {
			try {
				busLog.createNewFile();
			} catch (IOException e) {
			}
		}
		if (!metroLog.exists()) {
			try {
				metroLog.createNewFile();
			} catch (IOException e) {
			}
		}
		readInitFile();
		readAdminLog();
		readUserLog();
		readBusLog();
		readMetroLog();
	}

	public int getId() {
		return TransitSystem.id;
	}

	public void setId(int num) {
		TransitSystem.id = num;
		updateInitFile();
	}

	public float getCOST_PER_STATION() {
		return TransitSystem.COST_PER_STATION;
	}

	public float getBUS_FARE() {
		return TransitSystem.BUS_FARE;
	}

	public float getMETRO_FARE() {
		return TransitSystem.METRO_FARE;
	}

	public void readInitFile() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(initFile));
			String idInfo = bR.readLine();
			String operationalCost = bR.readLine();
			String busFare = bR.readLine();
			String metroFare = bR.readLine();
			TransitSystem.id = Integer.parseInt(idInfo.split("=")[1]);
			TransitSystem.COST_PER_STATION = Float.parseFloat(operationalCost.split("=")[1]);
			TransitSystem.BUS_FARE = Float.parseFloat(busFare.split("=")[1]);
			TransitSystem.METRO_FARE = Float.parseFloat(metroFare.split("=")[1]);
			bR.close();
		} catch (IOException e) {
		}
	}

	public void updateInitFile() {
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter(initFile));
			bW.write("id=" + TransitSystem.id + "\n");
			bW.write("COST_PER_STATION=" + TransitSystem.COST_PER_STATION + "\n");
			bW.write("BUS_FARE=" + TransitSystem.BUS_FARE + "\n");
			bW.write("METRO_FARE=" + TransitSystem.METRO_FARE + "\n");
			bW.flush();
			bW.close();
		} catch (IOException e) {
		}
	}

	public void updateAdminLog() {
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter(adminLog));
			for (String name : adminUsers.keySet()) {
				bW.write(adminUsers.get(name).getUsername() + ";;;" + adminUsers.get(name).getPassword() + "\n");
			}

			bW.flush();
			bW.close();
		} catch (IOException e) {
		}
	}

	public void updateUserLog() {
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter(userLog));
			for (String email : cHUsers.keySet()) {
				CardHolder user = cHUsers.get(email);
				StringBuilder info = new StringBuilder();
				info.append(user.getEmail() + ";;;" + user.getName());
				for (int id : user.getCards().keySet()) {
					Card card = user.getCard(id);
					info.append(";;;");
					info.append(id + "/");
					info.append(card.getBalance() + "/");
					info.append(card.isAccountOpen() + "/");
					info.append(card.isInTransit() + "/");
					info.append(card.getBeginTransitTime().format(DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss"))
							+ "/");
					info.append(card.getBeginTransitLocation() + "/");
					String[] recentTrips = card.getRecentTrips();
					info.append(recentTrips[0] + "|" + recentTrips[1] + "|" + recentTrips[2]);
				}
				bW.write(info.toString() + "\n");
			}
			bW.flush();
			bW.close();
		} catch (IOException e) {
		}

	}

	public void updateBusLog() {
		try {
			BufferedWriter bW = new BufferedWriter(new FileWriter(busLog));
			for (String b : buses.keySet()) {
				Bus bus = buses.get(b);
				StringBuilder info = new StringBuilder();
				info.append(bus.getName() + ";;;");
				String[] route = bus.getRoute();
				for (int i = 0; i < route.length; i++) {
					info.append(route[i] + "|");
				}
				info.append(";;;" + bus.getRevenue() + ";;;");
				info.append(bus.getLocationsReached() + ";;;");
				info.append(bus.getCurrentLocation());
				bW.write(info.toString() + "\n");
			}

			bW.flush();
			bW.close();
		} catch (IOException e) {
		}

	}

	public void updateMetroLog() {
		try {

			BufferedWriter bW = new BufferedWriter(new FileWriter(metroLog));
			for (String m : metro.keySet()) {
				Metro met = metro.get(m);
				StringBuilder info = new StringBuilder();
				info.append(met.getName() + ";;;");
				String[] route = met.getRoute();
				for (int i = 0; i < route.length; i++) {
					info.append(route[i] + "|");
				}
				info.append(";;;" + met.getRevenue() + ";;;");
				info.append(met.getLocationsReached() + ";;;");
				info.append(met.getCurrentLocation());
				bW.write(info.toString() + "\n");
			}
			bW.flush();
			bW.close();
		} catch (IOException e) {
		}
	}

	public void readAdminLog() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(adminLog));
			String line = bR.readLine();
			Admin admin;
			while (line != null) {
				String[] info = line.split(";;;");
				String username = info[0];
				String password = info[1];
				admin = new Admin(username, password, this);
				this.adminUsers.put(username, admin);
				line = bR.readLine();
			}
			bR.close();
		} catch (IOException e) {
		}
	}

	public void readUserLog() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(userLog));
			String line = bR.readLine();
			while (line != null) {
				CardHolder user;
				HashMap<Integer, Card> cards = new HashMap<>();
				String[] info = line.split(";;;");
				String email = info[0];
				String username = info[1];
				for (int i = 2; i < info.length; i++) {
					String[] cardInfo = info[i].split("\\/");
					int id = Integer.parseInt(cardInfo[0]);
					double bal = Double.parseDouble(cardInfo[1]);
					boolean accOpen = Boolean.parseBoolean(cardInfo[2]);
					boolean inTran = Boolean.parseBoolean(cardInfo[3]);
					LocalDateTime beginTime = LocalDateTime.parse(cardInfo[4],
							DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss"));
					int beginLocation = Integer.parseInt(cardInfo[5]);
					String[] recentTrips = cardInfo[6].split("\\|");
					Card card = new Card(id, bal, accOpen, inTran, beginTime, beginLocation, recentTrips);
					cards.put(id, card);
				}
				user = new CardHolder(email, username, cards);
				this.cHUsers.put(user.getEmail(), user);
				line = bR.readLine();
			}
			bR.close();
		} catch (IOException e) {
		}
	}

	public void readBusLog() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(busLog));
			String line = bR.readLine();
			Bus bus;
			while (line != null) {
				String[] info = line.split(";;;");
				String name = info[0];
				String[] route = info[1].split("\\|");
				double revenue = Double.parseDouble(info[2]);
				int locReached = Integer.parseInt(info[3]);
				int currLoc = Integer.parseInt(info[4]);
				bus = new Bus(name, route, this);
				this.buses.put(bus.getName(), bus);
				bus.addRevenue(revenue);
				bus.addLocationsReached(locReached);
				bus.setCurrentLocation(currLoc);
				line = bR.readLine();
			}
			bR.close();
		} catch (IOException e) {
		}
	}

	public void readMetroLog() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(metroLog));
			String line = bR.readLine();
			Metro metro;
			while (line != null) {
				String[] info = line.split(";;;");
				String name = info[0];
				String[] route = info[1].split("\\|");
				double revenue = Double.parseDouble(info[2]);
				int locReached = Integer.parseInt(info[3]);
				int currLoc = Integer.parseInt(info[4]);
				metro = new Metro(name, route, this);
				this.metro.put(metro.getName(), metro);
				metro.addRevenue(revenue);
				metro.addLocationsReached(locReached);
				metro.setCurrentLocation(currLoc);
				line = bR.readLine();
			}
			bR.close();
		} catch (IOException e) {
		}
	}

	public void readTransitLog() {
		try {
			BufferedReader bR = new BufferedReader(new FileReader(transitLog));
			String line = bR.readLine();
			while (line != null) {
				System.out.println(line);
				line = bR.readLine();
			}
			bR.close();
		} catch (IOException e) {
		}
	}

	public Admin adminSignUp(String username, String password) {
		if (!this.adminUsers.containsKey(username)) {
			Admin a = new Admin(username, password, this);
			this.adminUsers.put(username, a);
			updateAdminLog();
			return a;
		} else {
			return null;
		}
	}

	public Admin adminLogin(String username, String password) {
		if (this.adminUsers.containsKey(username) && this.adminUsers.get(username).getPassword().equals(password)) {
			return this.adminUsers.get(username);
		} else {
			return null;
		}
	}

	public CardHolder cHSignUp(String email, String name, int id) {
		if (!this.cHUsers.containsKey(email)) {
			Card card = new Card(id, 19.0f);
			CardHolder cH = new CardHolder(email, name, card);
			this.cHUsers.put(cH.getEmail(), cH);
			updateUserLog();
			return cH;
		} else {
			return null;
		}
	}

	public CardHolder cHLogin(String email) {
		if (this.cHUsers.containsKey(email)) {
			return this.cHUsers.get(email);
		} else {
			return null;
		}
	}

	public void openSystem() {
		this.transitSystemOpen = true;
	}

	public void closeSystem() {
		this.transitSystemOpen = false;
	}

	public void tapCard(Metro metro, int beginLocation, Card card) {
		if (!transitSystemOpen) {
			System.out.println("Error: System is closed.");
			return;
		} else if (card.isInTransit()) {
			// need to implement the change from bus to metro so this is used
			int inTransitMinutes = (int) Math
					.abs(Duration.between(card.getBeginTransitTime(), LocalDateTime.now()).toMinutes());
			int beginTransitLocation = card.getBeginTransitLocation();
			int currentLocation = metro.getCurrentLocation();

			float tripFare = metro.getFARE_COST() * (currentLocation - beginTransitLocation);
			tripFare = Math.min(6.0f, tripFare);

			card.endTransit(tripFare);
			card.addRecentTrip("On " + LocalDateTime.now().getMonth() + " " + LocalDateTime.now().getDayOfMonth() + " "
					+ card.getBeginTransitTime().getHour() + ":" + card.getBeginTransitTime().getMinute() + ":"
					+ card.getBeginTransitTime().getSecond() + " you took metro " + metro.getName() + " from station "
					+ metro.getRoute()[beginTransitLocation] + " to station " + metro.getRoute()[currentLocation]
					+ ", travelling " + (currentLocation - beginTransitLocation) + " station(s). The trip took "
					+ inTransitMinutes + " minute(s) to complete and costed you $" + tripFare + ".");

			metro.addRevenue(tripFare);
			metro.addLocationsReached(currentLocation - beginTransitLocation);

			try {
				BufferedWriter bW = new BufferedWriter(new FileWriter(transitLog, true));

				bW.write(LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth() + " "
						+ LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/"
						+ LocalDateTime.now().getSecond() + " " + card.getId() + " " + metro.getName() + " "
						+ metro.getRoute()[currentLocation] + " off" + "\n");
				bW.flush();
				bW.close();
			} catch (IOException e) {
			}

		} else {
			if (card.getBalance() > 0.0f && card.isAccountOpen()) {
				card.beginTransit(beginLocation, LocalDateTime.now());

				try {
					BufferedWriter bW = new BufferedWriter(new FileWriter(transitLog, true));

					bW.write(LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth() + " "
							+ LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/"
							+ LocalDateTime.now().getSecond() + " " + card.getId() + " " + metro.getName() + " "
							+ metro.getRoute()[metro.getCurrentLocation()] + " on" + "\n");
					bW.flush();
					bW.close();
				} catch (IOException e) {
				}

			} else {
				System.out.println("Your card does not have enough balance.(" + card.getBalance()
						+ ") Or the account is closed.");
			}

		}

	}

	public void tapCard(Bus bus, int beginLocation, Card card) {
		if (!transitSystemOpen) {
			System.out.println("Error: System is closed.");
			return;
		} else if (card.isInTransit()) {
			int inTransitMinutes = (int) Math
					.abs(Duration.between(card.getBeginTransitTime(), LocalDateTime.now()).toMinutes());
			int beginTransitLocation = card.getBeginTransitLocation();
			int currentLocation = bus.getCurrentLocation();

			float tripFare = 0.0f;

			if (currentLocation - beginTransitLocation != 0) {
				tripFare += bus.getFARE_COST();
			}

			card.endTransit(tripFare);

			card.addRecentTrip("On " + LocalDateTime.now().getMonth() + " " + LocalDateTime.now().getDayOfMonth() + " "
					+ card.getBeginTransitTime().getHour() + ":" + card.getBeginTransitTime().getMinute() + ":"
					+ card.getBeginTransitTime().getSecond() + " you took bus " + bus.getName() + " from stop "
					+ bus.getRoute()[beginTransitLocation] + " to stop " + bus.getRoute()[currentLocation]
					+ ", travelling " + (currentLocation - beginTransitLocation) + " stop(s). The trip took "
					+ inTransitMinutes + " minute(s) to complete and costed you $" + tripFare + ".");

			bus.addRevenue(bus.getFARE_COST());
			bus.addLocationsReached(currentLocation - beginTransitLocation);

			try {
				BufferedWriter bW = new BufferedWriter(new FileWriter(transitLog, true));

				bW.write(LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth() + " "
						+ LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/"
						+ LocalDateTime.now().getSecond() + " " + card.getId() + " " + bus.getName() + " "
						+ bus.getRoute()[currentLocation] + " off" + "\n");
				bW.flush();
				bW.close();
			} catch (IOException e) {
			}

		} else {
			if (card.getBalance() > 0.0f && card.isAccountOpen()) {
				card.beginTransit(beginLocation, LocalDateTime.now());
				try {
					BufferedWriter bW = new BufferedWriter(new FileWriter(transitLog, true));

					bW.write(LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getDayOfMonth() + " "
							+ LocalDateTime.now().getHour() + "/" + LocalDateTime.now().getMinute() + "/"
							+ LocalDateTime.now().getSecond() + " " + card.getId() + " " + bus.getName() + " "
							+ bus.getRoute()[bus.getCurrentLocation()] + " on" + "\n");
					bW.flush();
					bW.close();
				} catch (IOException e) {
				}
			} else {
				System.out.println("Your card does not have enough balance.(" + card.getBalance()
						+ ") Or the account is closed.");
			}
		}
	}

	public HashMap<String, Bus> getBuses() {
		return this.buses;
	}

	public HashMap<String, Metro> getMetro() {
		return this.metro;
	}

	public HashMap<String, CardHolder> getCH() {
		return this.cHUsers;
	}
}
