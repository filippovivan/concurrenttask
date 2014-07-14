package by.filippov.auction.enteties;

import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.Logger;

public class AuctionDirector {
	private static final Logger LOG = Logger.getLogger(AuctionDirector.class);

	private CyclicBarrier barrier;
	private Lot lot;

	public AuctionDirector(final Lot lot, int playersNumber) {
		super();
		this.lot = lot;
		barrier = new CyclicBarrier(playersNumber, new Runnable() {

			public void run() {
				LOG.info("Player " + lot.getLastPlayerRaised() + " won lot "
						+ lot + " for " + lot.getCurrentPrice());
			}

		});
	}

	public CyclicBarrier getBarrier() {
		return barrier;
	}

	public Lot getLot() {
		return lot;
	}

}
