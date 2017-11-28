package model;

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
	
	/**
	 * Constructor, must pass a Coin in to be analyzed
	 * @param coin The coin to be analyzed
	 */
	public Analyzer(Coin coin) {
		this.coin = coin;
		success = false;
		analyze();
	}
	/**
	 * Method to apply the algorithm to the coin data
	 */
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
	
	/**
	 * Returns the Volume of the coin
	 * @return double Coin Volume
	 */
	public double getVolume() {
		return this.volume;
	}
	/**
	 * Returns the High value of the coin
	 * @return double 24 hr High Value
	 */
	public double getHigh() {
		return this.high;
	}
	/**
	 * Returns the Low value of the coin
	 * @return double 24 hr Low Volume
	 */
	public double getLow() {
		return this.low;
	}
	/**
	 * Returns the 24 hr low value of the coin
	 * @return double 24 hr low value
	 */
	public double getValue() {
		return this.current;
	}
	/**
	 * Returns the name of the coin
	 * @return Name of the Coin
	 */
	public String getName() {
		return this.name;
	}
	/** 
	 * Returns the percent change from the last 24 hours low to high
	 * @return double percent change between low and high over last 24 hrs
	 */
	public double getPChange() {
		return this.pChange;
	}
	/**
	 * Calculates percent change
	 * @param oldVal Old Value
	 * @param newVal New Value
	 * @return double Percent Change between two values
	 */
	private double percentChange(double oldVal, double newVal) {
		return ((newVal - oldVal) / oldVal) * 100;
	}
	/**
	 * Checks to see if the coin meets the algorithm criteria
	 * @return boolean True if it meets the criteria
	 */
	public boolean checkForSuccess() {
		return this.success;
	}
}