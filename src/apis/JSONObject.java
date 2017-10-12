package apis;

import java.util.HashMap;

public class JSONObject {
	private static final int CHECK_CHAR = 11; 		//The 11th character of the String returned from the API call
													//is a success check, either t (for true) or f (for false). 
	private String data, tempKey = "99", tempVal = "99";
	private HashMap<String, String> jMap = new HashMap<String, String>();
	private int i, duplicates;
	
	// TODO: Create methods to return values from the Hash as called
	//Constructor
	public JSONObject(String rawData) {
		if (rawData == "" || rawData.charAt(CHECK_CHAR) != 't') {	
			returnFail();
		}
		else {
			this.data = rawData;
			this.i = 0;
			convert();
		}
	}
	
	public String toString() {
		return data;
	}
	
	public String getValue(String key) {
		return this.jMap.get(key);
	}
	
	private void returnFail() {
		System.err.println("Invalid value passsed upon creation");
		System.exit(1);			//Replace with different command on final implementation, just for testing
	}
	
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
					System.out.println(tempKey + " " + tempVal);
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
	}
}