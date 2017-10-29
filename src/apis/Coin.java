package apis;

import java.net.URL;
import java.util.Scanner;
public class Coin {

	private String coin;
	private URL url = null;
	private Scanner urlIn;
	private String urlData;
	private String apiKey;
	private JSONObject jObj;
	
	public Coin(String coin, String apiKey) {
		this.coin = coin;
		this.apiKey = apiKey;
	}
	
	private JSONObject getInfo(String sUrl) throws Exception {
		this.url = new URL(sUrl);
		this.urlIn = new Scanner(this.url.openStream());
		this.urlData = urlIn.next();
		JSONObject temp = new JSONObject(this.urlData);
		return temp;
		
	}
	
	public String getTicker(String key) throws Exception { 
		this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getticker?market=BTC-" + this.coin);
		return jObj.getValue(key);
	}
	
	public String getMarketSummary(String key) throws Exception {
		this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getmarketsummary?market=BTC-" + this.coin);
		return jObj.getValue(key);
	}
	
	public String getBalance() throws Exception {
		this.jObj = getInfo("https://bittrex.com/api/v1.1/account/getbalance?apikey=" + this.apiKey + "&currency=BTC");
		return jObj.getValue("Balance");
	}
	
	
	
}
