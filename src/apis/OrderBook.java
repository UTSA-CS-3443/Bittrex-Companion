package apis;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class OrderBook {

	private String market, type;
	private URL url;
	private Scanner urlIn = null;
	private String urlData;
	
	
	//Constructor
	public OrderBook(String market, String type) {
		if (type.equals("both")) {
			System.err.println("Unable to query both, please specify one or the other");
			System.exit(2);
		}
		this.market = market;
		this.type = type;
	}
	
	public String getInfo() {
		try {
			this.url = new URL("https://bittrex.com/api/v1.1/public/getorderbook?market=" + this.market + "&type=" + this.type);
			this.urlIn = new Scanner(url.openStream());
			this.urlData = urlIn.next();
		} catch (IOException ex) {
			if (this.urlIn == null) {
				this.urlData = "Failed to parse data";
			}
		} 
		//System.out.println(this.urlData);
		return this.urlData;
	}
}
