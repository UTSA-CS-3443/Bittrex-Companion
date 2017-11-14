package application;
import java.util.ArrayList;

/**
 * Class designed to handle the analysis of all the data and 
 * apply the trading algorithms
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal
 * @author Richard
 *
 */

public class Analyzer {
	private ArrayList<Double> rangeReturn;
	private double[] data;
	private double[] oldNew;
	private int[] indexRange;
	private int dataLength;
	private double percentThreshold;
	private boolean checkForThresh;
	
	/**
	 * Constructer for class. 
	 * 
	 * @param data Array of doubles containing the data you wish to analyze
	 * @param percentThreshold The threshold you wish to check against
	 */
	public Analyzer(double[] data, double percentThreshold) {
		this.data = data;
		this.oldNew = new double[2];
		this.dataLength = this.data.length;
		this.percentThreshold = percentThreshold;
		this.rangeReturn = new ArrayList<Double>();
		this.indexRange = new int[2];
	}
	
	/**
	 * The actual analysis method. Will check the array for a percent increase
	 * or decrease that matches the supplied parameters.
	 * @return boolean True if a match is found, false otherwise
	 */
	public boolean analyze() {
		int i;
		double tempPercentChange;
		for (i = 0; i < this.dataLength; i++) {
			if (data[i+1] > data[i]) {
				i = increasing(i);
			} else if (data[i+1] < data[i]){
				i = decreasing(i);
			}
			tempPercentChange = percentChange(oldNew[0], oldNew[1]);
			if (tempPercentChange > percentThreshold || tempPercentChange < percentThreshold) {
				checkForThresh = true;
				return true;
			}
		}
		checkForThresh = false;
		return false;
		
	}
	/**
	 * Method to return an ArrayList of the values in the range that meets the
	 * threshold. May be deprecated
	 * @return ArrayList<Double> The values that met the criteria
	 */
	public ArrayList<Double> getValueArray() {
		if (checkForThresh) {
			for (int i = indexRange[0]; i < indexRange[1]; i++) {
				this.rangeReturn.add(this.data[i]);
			}
			return this.rangeReturn;
		} 
		return null;
	}
	/**
	 * Find the range of increasing values
	 * @param startIndex Local minimum to begin the range
	 * @return int The ending index so the process can be repeated
	 */
	private int increasing(int startIndex) {
		int i = startIndex;
		while (data[i+1] > data[i]) {
			i++;
		}
		oldNew[0] = this.data[startIndex];
		oldNew[1] = this.data[i];
		indexRange[0] = startIndex;
		indexRange[1] = i;
		return i;
	}
	/**
	 * Find the range of decreasing values
	 * @param startIndex Local maximum to begin the range
	 * @return int The ending index so the process can be repeated
	 */
	private int decreasing(int startIndex) {
		int i = startIndex;
		while (data[i+1] < data[i]) {
			i++;
		}
		oldNew[0] = this.data[startIndex];
		oldNew[1] = this.data[i];
		indexRange[0] = startIndex;
		indexRange[1] = i;
		return i;
	}
	/**
	 * Method to calculate the percent change of a range of values
	 * Formula: (N - O) / O * 100
	 * @param oldVal The old value for the formula
	 * @param newVal The new value for the formula
	 * @return double The value of the percent change
	 */
	private double percentChange(double oldVal, double newVal) {
		return ((newVal - oldVal) / oldVal) * 100;
	}
	
}
