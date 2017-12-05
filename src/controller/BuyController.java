package controller;

import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Coin;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuyController {

	
	@FXML
	private Slider buySlider;
	@FXML 
	private Label selectedLabel;
	@FXML
	private Label balanceLabel;
	@FXML 
	private Button buyButton;
	
	private double balance;
	private String coin, apiKey, apiSecret, ask;
	private Coin thisCoin;
	
	
	public void passCoin(String coin, double balance, String ask) {
		this.coin = coin;
		this.ask = ask;
		this.balance = balance;
		this.balanceLabel.setText(String.valueOf(this.balance));
		this.buySlider.setMin(0);
		this.buySlider.setMax(this.balance);
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
	
	public void buyCoin() {
		this.thisCoin.buyCoin(String.valueOf(buySlider.getValue()), ask);
		try {
			Stage current = (Stage) this.buySlider.getScene().getWindow();
			current.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
