package application;
import java.util.ArrayList;
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
	
	private Coin[] coinsList;
	private ArrayList<Coin> coinsToReturn;
	private boolean success;
	private double volume, low, high, pChange, current;
	
	public Analyzer(Coin[] coinsList) {
		this.coinsList = coinsList;
		this.coinsToReturn = new ArrayList<Coin>();
		success = false;
		analyze();
		
	}
	
	private void analyze() {
		for (Coin temp : this.coinsList) {
			this.volume = Double.valueOf(temp.getMarketSummary("BaseVolume"));
			this.high = Double.valueOf(temp.getMarketSummary("High"));
			this.low = Double.valueOf(temp.getMarketSummary("Low"));
			this.current = Double.valueOf(temp.getMarketSummary("Last"));
			this.pChange = percentChange(this.low, this.high);
			if (this.pChange >= 5 && this.volume >= 300)
				coinsToReturn.add(temp);
		}
		if (coinsToReturn.size() >= 5)
			this.success = true;
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
	
	public ArrayList<Coin> getCoins() {
		return this.coinsToReturn;
	}
}