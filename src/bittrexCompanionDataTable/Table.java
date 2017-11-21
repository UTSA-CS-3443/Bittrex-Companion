package bittrexCompanionDataTable;
/**
 * @author oscar
 *
 *This class serves the purpose to construct the type of table used in our application .
 *It contains getters and setters necessary for the table function to our  current specifications.
 */
public class Table {

	private String coin;
	private double volume;
	private double high;
	private double pChange;
	private double low;

	public Table() {
		this.coin = "";
		this.volume = 0.0;
		this.high = 0.0;
		this.low = 0.0;
		this.pChange = 0.0;
	}

	public Table(String coin, double volume, double high, double low, double pChange) {
		this.coin = coin;
		this.volume = volume;
		this.high = high;
		this.low = low;
		this.pChange=pChange;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getpChange() {
		return pChange;
	}

	public void setpChange(double pChange) {
		this.pChange = pChange;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}
}