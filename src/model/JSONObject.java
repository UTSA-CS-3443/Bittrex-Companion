package model;

import java.util.HashMap;

/**
 * Class to take the JSONObject returned from Bittrex API calls and turn it into usable data
 * relevant to our app.
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */

public class JSONObject {
	private static final int CHECK_CHAR = 11; 		//The 11th character of the String returned from the API call
													//is a success check, either t (for true) or f (for false). 
	private String data, tempKey = "99", tempVal = "99";
	private HashMap<String, String> jMap = new HashMap<String, String>();
	private int i, duplicates;
	
	/**
	 * Constructor, requires the String version of the JSONObject
	 * 
	 * @param rawData String version of JSONObject
	 */
	public JSONObject(String rawData) {
		if (rawData == "" || rawData.charAt(CHECK_CHAR) != 't') {	
			String errData[] = rawData.split(",");
			String specErr[] = errData[1].split(":");
			String errOut = specErr[1];
			System.err.println("ERROR:" + errOut);
			returnFail();
		}
		else {
			this.data = rawData;
			this.i = 0;
			convert();
		}
	} // End Constructor

	public String toString() {
		return data;
	}

	/**
	 * Return a requested value from the HashMap
	 * 
	 * @param key Data to request from the Object
	 * @return the Value of the data
	 */
	public String getValue(String key) {
		return this.jMap.get(key);
	} // End getValue method
	
	/**
	 * Method used only upon failure, will be deprecated in future versions
	 */
	private void returnFail() {
		System.err.println("Invalid value passsed upon creation");
	} // End returnFail method
	
	/**
	 * The real work is all done here. Converts the JSON into a HashMap to make 
	 * requesting values easier
	 */
	private void convert() {
		try {
			
			tempKey = "99";
			tempVal = "99";
			String tempStr = new String();
			for (i = 0; i < this.data.length(); i++) {
				if (this.data.charAt(i) == '"') {
					i++;
					tempStr = "";
					while (this.data.charAt(i) != '"') {
						tempStr += this.data.charAt(i);
						i++;
					}
				}
				if (tempStr.equals("result")) {
					break;
				}
			}
			while (this.data.charAt(i) != '{') {
				i++;
			}
			i++;
			duplicates = 0;
			for (; i < this.data.length(); i++) {
				if (!this.tempKey.equals("99") && !this.tempVal.equals("99")) {
					if (jMap.containsKey(tempKey)) {
						tempKey += String.valueOf(duplicates);
						duplicates++;
					}
					jMap.put(this.tempKey, this.tempVal);
					tempKey = "99";
					tempVal = "99";
				}
				if (this.data.charAt(i) == '"') {
					i++;
					tempKey = "";
					while (this.data.charAt(i) != '"') {
						tempKey += this.data.charAt(i);
						i++;
					}
				}
				else if (this.data.charAt(i) == ':') {
					i++;
					tempVal = "";
					while (this.data.charAt(i) != ',') {
						if (this.data.charAt(i) == '}') 
							break;
						tempVal += this.data.charAt(i);
						i++;
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			//System.err.println("Did I break this part?");
		}
	} //End convert method
}