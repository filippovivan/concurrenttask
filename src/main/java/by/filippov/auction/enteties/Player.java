package by.filippov.auction.enteties;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Player {
	private static final double CHANCE_TO_STOP = 0.1;
	private static final Logger LOG = Logger.getLogger(Player.class);
	private static final int PRICE_LIMIT_DISPERSION = 100;
	private static final int INIT_PRICE_LIMIT = 50;

	private int priceLimit;
	private String playerName;
	private Random random;

	public Player(String name) {
		this.playerName = name;
		random = new Random();
		priceLimit = INIT_PRICE_LIMIT + random.nextInt(PRICE_LIMIT_DISPERSION);
	}

	public Thread getPlayerThread(final AuctionDirector director) {
		return new Thread() {
			@Override
			public void run() {
				Lot lot = director.getLot();
				try {
					while (random.nextDouble() > CHANCE_TO_STOP
							&& lot.getCurrentPrice() < priceLimit) {
						int amount;
						if (lot.getLastPlayerRaised() != Player.this) {
							amount = random.nextInt(20);
							lot.updatePrice(amount, Player.this);
							LOG.info(playerName + " raised for " + amount
									+ " on lot " + director.getLot());
							TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
						}
					}
					LOG.info(playerName + " end rising on lot "
							+ director.getLot());
					director.getBarrier().await();
				} catch (InterruptedException e) {
					LOG.warn(e);
				} catch (BrokenBarrierException e) {
					LOG.error(e);
				}
			}
		};
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean wannaPlay() {
		return random.nextDouble() > 0.5;
	}

	@Override
	public String toString() {
		return playerName;
	}

}
