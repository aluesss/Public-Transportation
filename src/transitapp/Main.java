package transitapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		TransitSystem system = new TransitSystem();
		int id = system.getId();

		while (true) {
			System.out.println(
					"  _____   ___    ___   _  _    ___    ___   _____           ___  __   __  ___   _____   ___  __  __          \r\n"
							+ " |_   _| | _ \\  /   \\ | \\| |  / __|  |_ _| |_   _|   o O O / __| \\ \\ / / / __| |_   _| | __||  \\/  |   o O O \r\n"
							+ "   | |   |   /  | - | | .` |  \\__ \\   | |    | |    o      \\__ \\  \\ V /  \\__ \\   | |   | _| | |\\/| |  o      \r\n"
							+ "  _|_|_  |_|_\\  |_|_| |_|\\_|  |___/  |___|  _|_|_  TS__[O] |___/  _|_|_  |___/  _|_|_  |___||_|__|_| TS__[O] \r\n"
							+ "_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"|{======_|\"\"\"\"\"_| \"\"\" _|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"_|\"\"\"\"\"|{======| \r\n"
							+ "\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-./o--000\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-\"`-0-0-./o--000' "
							+ "\nMain Menu:\nAre you a CardHolder, Admin, Bus, or Metro?");
			String user = scan.nextLine();

			if (user.equalsIgnoreCase("cardholder") || user.equalsIgnoreCase("admin") || user.equalsIgnoreCase("bus") || user.equalsIgnoreCase("metro")) {
				while (user.equalsIgnoreCase("bus")) {
					Bus bus = null;
					System.out.println("\n\n\n\n\n\nBus Menu:\n" + "Please enter the bus name:");
					String name = scan.nextLine();
					int run = 1;
					if (system.getBuses().containsKey(name)) {
						bus = system.getBuses().get(name);
						while (run==1) {
							System.out.println("\n\nWhat would you like to do today?");
							System.out.println("Available Commands:\n"
									+ "- Move             : Moves the bus ahead by 1\n"
									+ "- Current Location : Shows the current location of the bus on the route\n"
									+ "- Show Route       : Shows the route of the bus\n"
									+ "- Tap Card         : Allows a user to tap a card on the bus card terminal\n"
									+ "- Exit             : Exits and returns to the main menu\n\n");
							String command = scan.nextLine();
							switch (command.toLowerCase()) {
							case "move": {
								bus.moveVehicle();
								break;
							}
							case "current location" : {
								System.out.println("Name:"+bus.getName()+" | Route:"+Arrays.toString(bus.getRoute()));
								System.out.println("The bus is currently at "+bus.getRoute()[bus.getCurrentLocation()]);
								break;
							}
							case "show route" : {
								System.out.println("Name:"+bus.getName()+" | Route:"+Arrays.toString(bus.getRoute()));
								break;
							}
							case "tap card" : {
								System.out.println("What is the Card ID of the card being tapped on the terminal?");
								int found = 0;
								int cardId = -1;
								String cardIdInput = scan.nextLine();
								try {
									cardId = Integer.parseInt(cardIdInput);
									for (String email : system.getCH().keySet()) {
										HashMap<Integer, Card> cards = system.getCH().get(email).getCards();
										if (cards.containsKey(cardId)) {
											bus.tapCardTerminal(cards.get(cardId));
											found = 1;
											break;
										}
									}
									if (found == 0) {
										System.out.println("Error: Did Not Find a Card with ID: "+cardId);
									}
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Card ID Format!");
								}
								break;
							}
							case "exit" : {
								run=0;
								break;
							}
							default : {
								System.out.println("Error: Invalid Command Entered!");
								break;
							}
							}
						}
						
					}
					else {
						System.out.println("Error: A Bus with name "+name+ " does not exist!");
					}
					if (run==0) {
						break;
					}
				}
				
				while (user.equalsIgnoreCase("metro")) {
					Metro metro = null;
					System.out.println("\n\n\n\n\n\nMetro Menu:\n" + "Please enter the metro name:");
					String name = scan.nextLine();
					int run = 1;
					if (system.getMetro().containsKey(name)) {
						metro = system.getMetro().get(name);
						while (run==1) {
							System.out.println("\n\nWhat would you like to do today?");
							System.out.println("Available Commands:\n"
									+ "- Move             : Moves the metro ahead by 1\n"
									+ "- Current Location : Shows the current location of the metro on the route\n"
									+ "- Show Route       : Shows the route of the metro\n"
									+ "- Tap Card         : Allows a user to tap a card on the metro card terminal\n"
									+ "- Exit             : Exits and returns to the main menu\n\n");
							String command = scan.nextLine();
							switch (command.toLowerCase()) {
							case "move": {
								metro.moveVehicle();
								break;
							}
							case "current location" : {
								System.out.println("Name:"+metro.getName()+" | Route:"+Arrays.toString(metro.getRoute()));
								System.out.println("The metro is currently at "+metro.getRoute()[metro.getCurrentLocation()]);
								break;
							}
							case "show route" : {
								System.out.println("Name:"+metro.getName()+" | Route:"+Arrays.toString(metro.getRoute()));
								break;
							}
							case "tap card" : {
								System.out.println("What is the Card ID of the card being tapped on the terminal?");
								int found = 0;
								int cardId = -1;
								String cardIdInput = scan.nextLine();
								try {
									cardId = Integer.parseInt(cardIdInput);
									for (String email : system.getCH().keySet()) {
										HashMap<Integer, Card> cards = system.getCH().get(email).getCards();
										if (cards.containsKey(cardId)) {
											metro.tapCardTerminal(cards.get(cardId));
											found = 1;
											break;
										}
									}
									if (found == 0) {
										System.out.println("Error: Did Not Find a Card with ID: "+cardId);
									}
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Card ID Format!");
								}
								break;
							}
							case "exit" : {
								run=0;
								break;
							}
							default : {
								System.out.println("Error: Invalid Command Entered!");
								break;
							}
							}
						}
						
					}
					else {
						System.out.println("Error: A Metro with name "+name+ " does not exist!");
					}
					if (run==0) {
						break;
					}
				}
				
				while (user.equalsIgnoreCase("admin")) {
					Admin adminUser = null;
					System.out.println("\n\n\n\n\n\nAdmin User Menu:\n" + "Do you have an existing account? Y/N/Exit");
					String account = scan.nextLine();
					String quit = "n";
					if (account.equalsIgnoreCase("y")) {
						while (adminUser == null) {
							System.out.println("\n\n\nLog in:\n" + "Please type your username below and hit enter:");
							String username = scan.nextLine();
							System.out.println("\n\n\nLog in:\n" + "Please type your password below and hit enter:");
							String password = scan.nextLine();
							adminUser = system.adminLogin(username, password);
							if (adminUser == null) {
								System.out.println("Error: Invalid Username/Password! Please try again.");
								System.out.println("Would you like to return to the main menu? Y/N");
								quit = scan.nextLine();
								if (quit.equalsIgnoreCase("y")) {
									break;
								}
							} else {
								System.out.println("Login Successful!");
								System.out.println("Welcome! " + adminUser.getUsername() + "\n");
							}
						}
						if (quit.equalsIgnoreCase("y")) {
							break;
						}

						int run = 1;

						while (run == 1) {
							System.out.println("\n\nWhat would you like to do today?");
							System.out.println("Available Commands:\n"
									+ "- Get Revenue          : Gives you the information of today's revenue\n"
									+ "- Get Operational Cost : Gives you the information of today's operational cost\n"
									+ "- Open System          : Open up the system\n"
									+ "- Close System         : Shut down the system\n"
									+ "- Change Password      : Change your password\n"
									+ "- Show Vehicles        : Shows all current vehicles\n"
									+ "- Create Vehicle       : Create new bus or metro\n"
									+ "- Remove Vehicle       : Remove an exist bus or metro\n"
									+ "- View Users           : Gives you all the cardholders' names in the current transit system\n"
									+ "- Exit                 : Exits and returns to the admin user menu\n\n");

							String command = scan.nextLine();

							switch (command.toLowerCase()) {
							case "get revenue": {
								double result = adminUser.getRevenue();
								System.out.println("Today's revenue is $" + result);
								break;
							}
							case "get operational cost": {
								double result = adminUser.getOperationalCost();
								System.out.println("Today's operational cost is $" + result);
								break;
							}
							case "open system": {
								adminUser.openSystem();
								System.out.println("System is now opened");
								break;
							}
							case "close system": {
								adminUser.closeSystem();
								System.out.println("System is now closed");
								break;
							}
							case "change password": {
								System.out.println("Please enter the new password:");
								String newPassword = scan.nextLine();
								adminUser.setPassword(newPassword);
								System.out.println("Your password is successfully changed");
								system.updateAdminLog();
								break;
							}
							case "show vehicles": {
								HashMap<String, Bus> buses = system.getBuses();
								HashMap<String, Metro> metro = system.getMetro();
								for (String name : buses.keySet()) {
									System.out.println("Bus name: " + name + " | Bus route: "
											+ Arrays.toString(buses.get(name).getRoute()));
								}
								for (String name : metro.keySet()) {
									System.out.println("Metro name: " + name + " | Metro route: "
											+ Arrays.toString(metro.get(name).getRoute()));
								}
								break;
							}
							case "create vehicle": {
								System.out.println("Please enter the type of vehicle you want to create: (Bus/Metro)");
								String choice = scan.nextLine();
								if (choice.equalsIgnoreCase("bus")) {
									System.out.println(
											"Please enter the name of the vehicle and the route that the vehicle will go");
									System.out.print("Name of the Vehicle:");
									String name = scan.nextLine();
									System.out.println("Please enter the number of stops for this route");
									int num = 0;
									String numInput = scan.nextLine();
									try {
										num = Integer.parseInt(numInput);
										String[] route = new String[num];
										for (int i = 0; i < route.length; i++) {
											System.out.println("Please enter the stop name for stop number " + (i + 1));
											route[i] = scan.nextLine();
										}
										Bus b = adminUser.createBus(name, route);
										System.out.println("Bus, " + b.getName() + ", has been created with route, "
												+ Arrays.toString(b.getRoute()));
										system.updateBusLog();
									} catch (NumberFormatException e) {
										System.out.println("Error: Invalid Input!");
									}
								} else if (choice.equalsIgnoreCase("metro")) {
									System.out.println(
											"Please enter the name of the vehicle and the route that the vehicle will go");
									System.out.print("Name of the Vehicle:");
									String name = scan.nextLine();
									System.out.println("Please enter the number of stations for this route");
									int num = 0;
									String numInput = scan.nextLine();
									try {
										num = Integer.parseInt(numInput);
										String[] route = new String[num];
										for (int i = 0; i < route.length; i++) {
											System.out.println(
													"Please enter the station name for station number " + (i + 1));
											route[i] = scan.nextLine();
										}
										Metro m = adminUser.createMetro(name, route);
										System.out.println("Metro, " + m.getName() + ", has been created with route, "
												+ Arrays.toString(m.getRoute()));
										system.updateMetroLog();
									} catch (NumberFormatException e) {
										System.out.println("Error: Invalid Input!");
									}
								}
								break;
							}
							case "remove vehicle": {
								System.out.println("Please enter the type of vehicle you want to remove: (Bus/Metro)");
								String choice = scan.nextLine();
								if (choice.equalsIgnoreCase("bus")) {
									System.out.println("Please enter the name of the bus");
									String name = scan.nextLine();
									adminUser.removeBus(name);
									System.out.println("Bus, " + name + ", has been removed");
									system.updateBusLog();
								} else if (choice.equalsIgnoreCase("metro")) {
									System.out.println("Please enter the name of the metro");
									String name = scan.nextLine();
									adminUser.removeMetro(name);
									System.out.println("Metro, " + name + ", has been removed");
									system.updateMetroLog();
								}
								break;
							}
							case "view users": {
								ArrayList<String> userInformation = adminUser.viewUsers();
								for (String s : userInformation) {
									System.out.println(s);
								}
								break;
							}
							case "exit": {
								run = 0;
								System.out.println("You have successfully returned to the admin user menu!");
								break;
							}
							default: {
								System.out.println("Error: Invalid Command Entered!");
								break;
							}
							}
						}
					} else if (account.equalsIgnoreCase("n")) {
						int accountCreated = 0;
						while (accountCreated == 0) {
							System.out.println("\n\n\nSign Up:\n" + "Please enter your username below and hit enter:");
							String username = scan.nextLine();
							System.out.println("Please enter a password for your account below:");
							String password = scan.nextLine();

							adminUser = system.adminSignUp(username, password);
							if (adminUser != null) {
								accountCreated = 1;
								System.out.println(
										"You account with username " + username + " has been successfully created!");
							} else {
								System.out.println(
										"Error: Invalid Username Entered! (Account with entered username already exists)");
							}
						}
					} else if (account.equalsIgnoreCase("exit")) { // exit
						System.out.println("Are you sure you want to return to the main menu? y/n");
						String end = scan.nextLine();
						if (end.equalsIgnoreCase("y")) {
							System.out.println("You have successfully returned to the main menu!");
							break;
						} else if (end.equalsIgnoreCase("n")) {
							System.out.println("Returning to the admin user menu!");
						} else {
							System.out.println("Error: Invalid Input! Please try again.");
						}
					} else {
						System.out.println("Error: Invalid Input! Please try again.");
					}
				}

				while (user.equalsIgnoreCase("cardholder")) {

					CardHolder cardHolder = null;
					System.out.println("\n\n\n\n\n\nCard Holder Menu:\n" + "Do you have an existing account? Y/N/Exit");
					String account = scan.nextLine();
					String quit = "n";

					if (account.equalsIgnoreCase("y")) { // user has an existing account

						while (cardHolder == null) {
							System.out.println("\n\n\nLog in:\n" + "Please type your email below and hit enter:");
							String email = scan.nextLine();

							cardHolder = system.cHLogin(email);

							if (cardHolder == null) {
								System.out.println("Error: Invalid Email! Please try again.");
								System.out.println("Would you like to return to the main menu? Y/N");
								quit = scan.nextLine();
								if (quit.equalsIgnoreCase("y")) {
									break;
								}
							} else {
								System.out.println("Login Successful!");
								System.out.println(
										"Welcome! " + cardHolder.getName() + " (" + cardHolder.getEmail() + ")\n");
							}

						}

						if (quit.equalsIgnoreCase("y")) {
							break;
						}

						int run = 1;

						while (run == 1) {
							System.out.println("\n\nWhat would you like to do today?");
							System.out.println("Available Commands:\n" 
									+ "- Add Card     : Creates a new card\n"
									+ "- Remove Card  : Deletes an existing card\n"
									+ "- Show Cards   : Shows all cards on your account\n"
									+ "- Recent Trips : Gives you the 3 most recent trips of a chosen card\n"
									+ "- Add Balance  : Recharge card balance\n" 
									+ "- SuspendCard  : Suspends a card\n"
									+ "- Open Card    : Opens a suspended card\n"
									+ "- Exit         : Exits and returns to the Card Holder menu\n\n");

							String command = scan.nextLine();

							switch (command.toLowerCase()) {
							case "add card": {
								Card card = new Card(id, 19.0f);
								cardHolder.addCard(card);
								System.out
										.println("Card with id " + id + " has been created and added to your account.");
								id += 1;
								system.setId(id);
								system.updateUserLog();
								break;
							}
							case "remove card": {
								if (cardHolder.getCardArray().size() > 1) {
									System.out.println(cardHolder.getCardInfo());
									System.out.println(
											"Type in the index number of the card you want removed from your account:");
									int index = -1;
									String indexInput = scan.nextLine();
									try {
										index = Integer.parseInt(indexInput);
									} catch (NumberFormatException e) {
										System.out.println("Error: Invalid Input!");
									}
									if (index != -1 && cardHolder.getCardArray().size() > 0
											&& cardHolder.getCardArray().get(index) != null) {
										System.out
												.println("Card with id " + cardHolder.getCardArray().get(index).getId()
														+ " has been removed from your account.");
										cardHolder.removeCard(cardHolder.getCardArray().get(index).getId());
										system.updateUserLog();
									} else {
										System.out.println("Error: Invalid Index Entered!");
									}
								} else {
									System.out.println("Error: You Must Have At Least 1 Card On Your Account!");
								}
								break;
							}
							case "show cards": {
								System.out.println(cardHolder.getCardInfo());
								break;
							}
							case "recent trips": {
								System.out.println(cardHolder.getCardInfo());
								System.out.println("Type in the index number of the card info you want to view:");
								int index = -1;
								String indexInput = scan.nextLine();
								try {
									index = Integer.parseInt(indexInput);
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Input!");
								}
								if (index != -1 && cardHolder.getCardArray().size() > 0
										&& cardHolder.getCardArray().get(index) != null) {
									String[] trips = cardHolder.getCardArray().get(index).getRecentTrips();
									for (String trip : trips) {
										if (trip == null) {
											System.out.println("No Trips Recorded.");
										} else {
											System.out.println(trip);
										}
									}
								}
								break;
							}
							case "add balance": {
								System.out.println(cardHolder.getCardInfo());
								System.out.println("Type in the index number of the card you want recharged:");
								int index = -1;
								String indexInput = scan.nextLine();
								try {
									index = Integer.parseInt(indexInput);
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Input!");
								}
								if (index != -1 && cardHolder.getCardArray().size() > 0
										&& cardHolder.getCardArray().get(index) != null) {
									System.out.println("Type in the amount of money that you want to charge:\n"
											+ "$10\n" + "$20\n" + "$50");
									float bal = scan.nextFloat();
									scan.nextLine();
									if (bal == 10.0f) {
										cardHolder.addBalance(cardHolder.getCardArray().get(index).getId(), bal);
									} else if (bal == 20.0f) {
										cardHolder.addBalance(cardHolder.getCardArray().get(index).getId(), bal);
									} else if (bal == 50.0f) {
										cardHolder.addBalance(cardHolder.getCardArray().get(index).getId(), bal);
									} else {
										System.out.println("Error: Invalid Amount Entered!");
									}
									system.updateUserLog();
								} else {
									System.out.println("Error: Invalid Index Entered!");
								}
								break;
							}
							case "suspend card": {
								System.out.println(cardHolder.getCardInfo());
								System.out.println("Type in the index number of the card you want suspended:");
								int index = -1;
								String indexInput = scan.nextLine();
								try {
									index = Integer.parseInt(indexInput);
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Input!");
								}
								if (index != -1 && cardHolder.getCardArray().size() > 0
										&& cardHolder.getCardArray().get(index) != null) {
									cardHolder.suspendCard(cardHolder.getCardArray().get(index).getId());
									system.updateUserLog();
								} else {
									System.out.println("Error: Invalid Index Entered!");
								}
								break;
							}
							case "open card": {
								System.out.println(cardHolder.getCardInfo());
								System.out.println("Type in the index number of the card you want opened:");
								int index = -1;
								String indexInput = scan.nextLine();
								try {
									index = Integer.parseInt(indexInput);
								} catch (NumberFormatException e) {
									System.out.println("Error: Invalid Input!");
								}
								if (index != -1 && cardHolder.getCardArray().size() > 0
										&& cardHolder.getCardArray().get(index) != null) {
									cardHolder.openCard(cardHolder.getCardArray().get(index).getId());
									system.updateUserLog();
								} else {
									System.out.println("Error: Invalid Index Entered!");
								}
								break;
							}
							case "exit": {
								run = 0;
								System.out.println("You have successfully returned to the card holder menu!");
								break;
							}
							default: {
								System.out.println("Error: Invalid Command Entered!");
								break;
							}
							}
						}

					}

					else if (account.equalsIgnoreCase("n")) { // user does not have an existing account

						int accountCreated = 0;
						while (accountCreated == 0) {
							System.out.println(
									"\n\n\nSign Up:\n" + "Please type your email address below and hit enter:");
							String email = scan.nextLine();
							System.out.println("Please type a name for your account below:");
							String name = scan.nextLine();

							cardHolder = system.cHSignUp(email, name, id);
							if (cardHolder != null) {
								accountCreated = 1;
								id += 1;
								system.setId(id);
								System.out.println("You account with email " + email + " and name " + name
										+ " has been successfully created!");
							} else {
								System.out.println(
										"Error: Invalid Email Entered! (Account with entered email already exists)");
							}
						}
					}

					else if (account.equalsIgnoreCase("exit")) { // exit
						System.out.println("Are you sure you want to return to the main menu? y/n");
						String end = scan.nextLine();
						if (end.equalsIgnoreCase("y")) {
							System.out.println("You have successfully returned to the main menu!");
							break;
						} else if (end.equalsIgnoreCase("n")) {
							System.out.println("Returning to the card holder menu!");
						} else {
							System.out.println("Error: Invalid Input! Please try again.");
						}
					}

					else {
						System.out.println("Error: Invalid Input! Please try again.");
					}
				}
			} else {
				System.out.println("Error: Invalid Input! Please try again.");
			}
		}
	}
}
