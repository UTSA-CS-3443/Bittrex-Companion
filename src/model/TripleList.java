package model;

/**
 * Custom List to handle sorting for "best" coins
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */
public class TripleList implements Comparable<TripleList> {

	private String name, pChange;
	private double volume;
	
	public TripleList(String name, double volume, String pChange) {
		this.name = name;
		this.volume = volume;
		this.pChange = pChange;
	}
	
	/**
	 * Gets the value of name
	 * @return String name of the coin
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets the value of the volume
	 * @return double Volume of the coin
	 */
	public double getVolume() {
		return this.volume;
	}
	/**
	 * Gets the value of percent Change
	 * @return String Percent Change
	 */
	public String getPChange() {
		return this.pChange;
	}
	
	/**
	 * Implementation of compareTo, required by the interface collections.Comparable
	 */
	@Override
	public int compareTo(TripleList other) {
		if (this.volume < other.getVolume()) 
			return -1;
		else if (this.volume > other.getVolume())
			return 1;
		return 0;
	}
}
