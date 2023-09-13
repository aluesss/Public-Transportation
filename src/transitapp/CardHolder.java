package transitapp;

import java.util.ArrayList;
import java.util.HashMap;

public class CardHolder {
	private String name;
	private String email;
	private HashMap<Integer, Card> cards = new HashMap<>();

	public CardHolder(String email, String name, Card card) {
		this.email = email;
		this.name = name;
		this.cards.put(card.getId(), card);
	}

	public CardHolder(String email, String name, HashMap<Integer, Card> cards) {
		this.email = email;
		this.name = name;
		this.cards = cards;
	}

	public String getName() {
		// returns name
		return this.name;
	}

	public void setName(String name) {
		// sets name
		this.name = name;
	}

	public String getEmail() {
		// returns email
		return this.email;
	}

	public Card getCard(int id) {
		// returns card given the id
		return this.cards.get(id);
	}

	public void addCard(Card card) {
		// adds a card to the user's account
		this.cards.put(card.getId(), card);
	}

	public void removeCard(int id) {
		// removes a card from the user's account
		this.cards.remove(id);
	}

	public ArrayList<Card> getCardArray() {
		ArrayList<Card> cards = new ArrayList<>();
		for (int id : this.cards.keySet()) {
			cards.add(this.cards.get(id));
		}
		return cards;
	}

	public HashMap<Integer, Card> getCards() {
		return this.cards;
	}

	public String getCardInfo() {
		String c = "";
		int num = 0;
		for (int id : this.cards.keySet()) {
			c += "index: " + num + "\n| card id     : " + id + "\n| card balance: " + this.cards.get(id).getBalance()
					+ "\n| account open: " + this.cards.get(id).isAccountOpen() + "\n";
			num += 1;
		}
		return c;

	}

	public void addBalance(int id, float balance) {
		// adds charge to a given card
		Card card = this.cards.get(id);
		card.rechargeBalance(balance);
		System.out.println("$" + balance + " has been successfully added to your card (" + id + ") account.");
	}

	public void getBalance(int id) {
		// returns balance of a card
		double balance = this.cards.get(id).getBalance();
		System.out.println("Your card (" + id + ") has $" + balance + " remaining balance.");
	}

	public void suspendCard(int id) {
		// closes the account of a card
		this.cards.get(id).closeAccount();
		System.out.println("Your card (" + id + ") has been successfully suspended.");
	}

	public void openCard(int id) {
		// opens the account of a card
		this.cards.get(id).openAccount();
		System.out.println("Your card (" + id + ") has been successfully suspended.");
	}

}
