package apis;

import java.net.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.security.*;
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
	
	public String getTicker(String key) { 
		try {
			this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getticker?market=BTC-" + this.coin);
			return jObj.getValue(key);
		} catch (Exception e) {
			//Something seriously wrong here, show me the stack trace!
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	}
	
	public String getMarketSummary(String key) {
		try {
			this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getmarketsummary?market=BTC-" + this.coin);
			return jObj.getValue(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	}
	
	public String getBalance() {
		try {
			//TODO: Need to hash and such, waiting on response from Bittrex
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	}
	
	
	
}
