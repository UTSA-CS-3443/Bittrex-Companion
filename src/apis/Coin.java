package apis;

import java.io.InputStream;
import java.net.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
import java.security.*;
public class Coin {

	private String coin;
	private URL url = null;
	private Scanner urlIn;
	private String urlData;
	private String apiKey;
	private String apiSecret;
	private JSONObject jObj;
	
	public Coin(String coin, String apiKey, String apiSecret) {
		this.coin = coin;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
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
			String nonce = GetNonce();
			//String apikey = "efbb2db14372420cb999b867e289c692";
			//String apisecret = "56c6a978e67544c6a34a7abe9fb64e45";
			String uri = "https://bittrex.com/api/v1.1/account/getbalance?apikey=" + this.apiKey + "&currency=BTC&nonce=" + nonce;
			String apisign = calculateHMAC(uri, this.apiSecret);
			String rawData = "";
			String balRet;
			URL url = new URL(uri);
			URLConnection con = url.openConnection();
			con.addRequestProperty("apikey", this.apiKey);
			con.addRequestProperty("apisecret", this.apiSecret);
			con.addRequestProperty("nonce", nonce);
			con.addRequestProperty("apisign", apisign);
			if (!con.getDoOutput()) {
				con.setDoOutput(true);
			}
			InputStream is = con.getInputStream();
			Scanner in = new Scanner(is);
			while (in.hasNext()) {
				rawData += in.next();
			}
			
			JSONObject tempObj = new JSONObject(rawData);
			if (tempObj.getValue("Balance").equals("null")) {
				return "0";
			} 
			return tempObj.getValue("Balance");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	}
	
	private static String GetNonce() {
		Date today = new Date();
		long ms = today.getTime();
		
		return String.valueOf(ms);
	}
	private static String toHexString(byte[] bytes) {
	    Formatter formatter = new Formatter();
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	    return formatter.toString();
	}

	private static String calculateHMAC(String data, String key)
	    throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
		String HMAC_SHA512 = "HmacSHA512";
	    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
	    Mac mac = Mac.getInstance(HMAC_SHA512);
	    mac.init(secretKeySpec);
	    return toHexString(mac.doFinal(data.getBytes()));
	}
	
	
	
}
