package application;

import apis.Coin;

/**
 * Class designed to handle the analysis of all the data and 
 * apply the trading algorithms
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */

public class Analyzer {
	
	private Coin coin;
	private boolean success;
	private String name;
	private double volume, low, high, pChange, current;
	
	public Analyzer(Coin coin) {
		this.coin = coin;
		success = false;
		analyze();
	}
	
	private void analyze() {
		this.name = coin.getMarketSummary("MarketName");
		this.volume = Double.valueOf(this.coin.getMarketSummary("BaseVolume"));
		this.high = Double.valueOf(this.coin.getMarketSummary("High"));
		this.low = Double.valueOf(this.coin.getMarketSummary("Low"));
		this.current = Double.valueOf(this.coin.getMarketSummary("Last"));
		this.pChange = percentChange(this.low, this.high);
		if (this.pChange >= 5 && this.volume > 300)
			this.success = true;
	}
	public double getVolume() {
		return this.volume;
	}
	public double getHigh() {
		return this.high;
	}
	public double getLow() {
		return this.low;
	}
	public double getValue() {
		return this.current;
	}
	public String getName() {
		return this.name;
	}
	public double getPChange() {
		return this.pChange;
	}
	
	public double priorityScore() {
		return percentChange(this.low, this.current);
	}
	
	private double percentChange(double oldVal, double newVal) {
		return ((newVal - oldVal) / oldVal) * 100;
	}
	
	public boolean checkForSuccess() {
		return this.success;
	}
}