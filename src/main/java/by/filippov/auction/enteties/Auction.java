package by.filippov.auction.enteties;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Auction extends Thread {
	private static final Logger LOG = Logger.getLogger(Auction.class);
	private ArrayList<Lot> lots = new ArrayList<Lot>();
	private ArrayList<Player> players = new ArrayList<Player>();

	@Override
	public void run() {
		for (Lot lot : lots) {
			AuctionDirector director = new AuctionDirector(lot, players.size());
			for (Player player : players) {
				LOG.info("Player " + player + " starts bid for " + lot);
				player.getPlayerThread(director).start();
			}
		}
	}

	public boolean add(Lot lot) {
		return lots.add(lot);
	}

	public boolean add(Player person) {
		return players.add(person);
	}
}
