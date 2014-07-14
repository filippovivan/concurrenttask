package by.filippov.auction.launching;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

import by.filippov.auction.enteties.Auction;
import by.filippov.auction.enteties.Lot;
import by.filippov.auction.enteties.Player;

public class AuctionLauncher {
	static {
		try (InputStream config = AuctionLauncher.class
				.getResourceAsStream("/logconfig.properties")) {
			PropertyConfigurator.configure(config);
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		Auction auction = new Auction();
		auction.add(new Player("Iosif"));
		auction.add(new Player("Kobzon"));
		auction.add(new Player("Alla"));
		auction.add(new Player("Pugacheva"));
		auction.add(new Player("Ivan"));
		auction.add(new Player("Dorn"));
		auction.add(new Lot(12, 80, "King's pants"));
		auction.add(new Lot(32, 90, "Golden egg"));
		auction.add(new Lot(42, 100, "Dead rat"));
		auction.add(new Lot(67, 12, "Bugatty"));
		auction.start();
	}
}
