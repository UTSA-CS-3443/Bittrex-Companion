package apis;

import java.io.InputStream;
import java.net.*;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;
import java.security.*;
/**
 * Class to handle all the coins information and to call all the Bittrex APIs
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 */
public class Coin {

	private String coin;
	private URL url = null;
	private Scanner urlIn;
	private String urlData;
	private String apiKey;
	private String apiSecret;
	private JSONObject jObj;
	
	/**
	 * Constructor for coin object that initializes the following parameters 
	 *
	 * @param coin The name of the coin
	 * @param apiKey APIKey from Bittrex
	 * @param apiSecret APISecret from Bittrex
	 */
	public Coin(String coin, String apiKey, String apiSecret) {
		this.coin = coin;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
	} // End 3-argument coin constructor
	
	/**
	 * Method getInfo takes a string sUrl to get the info needed to call the APIs
	 * 
	 * @param sUrl Base String for the URL, provided by Bittrex
	 * @return JSONObject To take the data and analyze it
	 */
	private JSONObject getInfo(String sUrl) {
		JSONObject temp = null;
		try {
			this.url = new URL(sUrl);
			this.urlIn = new Scanner(this.url.openStream());
			this.urlData = urlIn.next();
			temp = new JSONObject(this.urlData);
		} catch (MalformedURLException e) {
			//Should never happen
		} catch (IOException e) {
			//Should also never happen
		}
		return temp;	
	} // End getInfo method
	
	/**
	 * Method getTicker takes a key and returns a string to get the values of the coin
	 * 
	 * @param key Which data to pull from the call
	 * @return The requested value
	 */
	public String getTicker(String key) { 
		try {
			this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getticker?market=BTC-" + this.coin);
			return jObj.getValue(key);
		} catch (Exception e) {
			//Something seriously wrong here, show me the stack trace!
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	} //End getTicker method
	
	/**
	 * Method getmarketSummary grabs the info of a coin from bittrex and returns lots of data
	 * 
	 * @param key parameter to request data
	 * @return value of the requested data
	 */
	public String getMarketSummary(String key) {
		try {
			this.jObj = getInfo("https://bittrex.com/api/v1.1/public/getmarketsummary?market=BTC-" + this.coin);
			return jObj.getValue(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	} // End getMarketSummary method
	
	/**
	 * Method to get your balance in BTC
	 * @return String representation of your balance in BTC
	 */
	
	public String getBalance() {
		try {
			String nonce = GetNonce();
			//String apikey = "efbb2db14372420cb999b867e289c692";
			//String apisecret = "56c6a978e67544c6a34a7abe9fb64e45";
			String uri = "https://bittrex.com/api/v1.1/account/getbalance?apikey=" + this.apiKey + "&currency=BTC&nonce=" + nonce;
			String apisign = calculateHMAC(uri, this.apiSecret);
			String rawData = "";
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
			in.close();
			JSONObject tempObj = new JSONObject(rawData);
			if (tempObj.getValue("Balance").equals("null")) {
				return "0";
			} 
			return tempObj.getValue("Balance");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Something went seriously wrong here, investigate";
	} //End getBalance() method
	
	/**
	 * Nonce is part of the encryption algorithm to retrieve your balance
	 * Value is the number of seconds from Unix Epoch
	 * 
	 * @return String number of seconds from Unix Epoch until now
	 */
	
	private static String GetNonce() {
		Date today = new Date();
		long ms = today.getTime();
		
		return String.valueOf(ms);
	} //End GetNonce() method
	
	/**
	 * Convert a byte array to a String of Hexadecimal
	 * 
	 * @param bytes byte array of the original string
	 * @return String of hex values, converted from the original
	 */
	private static String toHexString(byte[] bytes) {
		String retVal;
	    Formatter formatter = new Formatter();
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	    retVal = formatter.toString();
	    formatter.close();
	    return retVal;
	} // End of toHexString() method

	/**
	 * Method to calculate an HMAC 
	 * 
	 * @param data String to be converted
	 * @param key key to use for conversion
	 * @return Encrypted String
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private static String calculateHMAC(String data, String key)
	    throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
	{
		String HMAC_SHA512 = "HmacSHA512";
	    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
	    Mac mac = Mac.getInstance(HMAC_SHA512);
	    mac.init(secretKeySpec);
	    return toHexString(mac.doFinal(data.getBytes()));
	} // End of calculateHMAC method
}