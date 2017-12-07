package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Coin;

/**
 * Controller class for the Sell Screen
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */

public class SellController {

	@FXML private Slider percentSlider;
	@FXML private Button sellButton;
	
	private double balance;
	private String coin, apiKey, apiSecret;
	private Coin thisCoin;
	/**
	 * "Initialization" method, used to gather all necessary information
	 * 
	 * @param coin Name of the coin
	 * @param balance Your coin balance
	 * @param ask Rate to sell the coin
	 */
	public void passCoin(String coin, double balance, String ask) {
		this.coin = coin;
		this.balance = balance;
		
		setupTicker();
		buildCoin();
	}
	/**
	 * Class to gather all information for the coin to sell
	 */
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
	
	/**
	 * Used to set all the values of the ticker on the screen
	 */
	private void setupTicker() {
		this.percentSlider.setMax(50);
		this.percentSlider.setShowTickLabels(true);
		this.percentSlider.setShowTickMarks(true);
		this.percentSlider.setBlockIncrement(5);
		for (int i = 10; i < 50; i++) {
			this.percentSlider.setMajorTickUnit(i);
		}
	}
	/**
	 * Actually performs the sell action
	 */
	public void sellCoin() {
		this.thisCoin.sellCoin(String.valueOf(balance), String.valueOf(percentSlider.getValue()));
		try {
			Stage current = (Stage) sellButton.getScene().getWindow();
			current.close();
		} catch (Exception e) {
			System.out.println("AHHH");
		}
	}
}
