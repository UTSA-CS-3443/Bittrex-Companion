package apis;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Ticker {

	private String market;
	private URL url;
	private Scanner urlIn = null;
	private String urlData;
	
	
	//Constructor
	public Ticker(String market) {
		this.market = market;
	}
	
	public String getInfo() {
		try {
			this.url = new URL("https://bittrex.com/api/v1.1/public/getticker?market=" + this.market);
			this.urlIn = new Scanner(url.openStream());
			this.urlData = urlIn.next();
		} catch (IOException ex) {
			if (this.urlIn == null) {
				this.urlData = "Failed to parse data";
			}
		} 
		
		return this.urlData;
	}
}
