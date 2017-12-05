package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Coin;

public class SellController {

	private double balance;
	private String coin, apiKey, apiSecret, ask;
	private Coin thisCoin;
	
	public void passCoin(String coin, double balance, String ask) {
		this.coin = coin;
		this.ask = ask;
		this.balance = balance;
		buildCoin();
	}
	
	private void buildCoin() {
		try {
			this.coin = this.coin.split("-")[1].replace('"', ' ').trim();
			File inFile = new File("UserInfo.txt");
			Scanner fileIn = new Scanner(inFile);
			fileIn.next();
			this.apiKey = fileIn.next();
			this.apiSecret = fileIn.next();
			fileIn.close();
		} catch (FileNotFoundException e) {
			
		}
		
		this.thisCoin = new Coin(this.coin, apiKey, apiSecret);
	}
}
