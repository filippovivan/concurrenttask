package by.filippov.auction.enteties;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lot {
	private int id;
	private int startPrice;
	private int currentPrice;
	private String description;
	private Lock locking;
	private Player lastPlayerRaised;

	public Lot(int id, int startPrice, String description) {
		super();
		this.id = id;
		this.startPrice = startPrice;
		this.description = description;
		locking = new ReentrantLock();
	}

	public Lot() {
		super();
		locking = new ReentrantLock();
	}

	public void updatePrice(int addition, Player player) {
		try {
			locking.lock();
			currentPrice += addition;
			lastPlayerRaised = player;
		} finally {
			locking.unlock();
		}
	}

	public int getId() {
		return id;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public String getDescription() {
		return description;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Player getLastPlayerRaised() {
		return lastPlayerRaised;
	}

	@Override
	public String toString() {
		return "Lot [id=" + id + ", startPrice=" + startPrice
				+ " " + description + "]";
	}
}
