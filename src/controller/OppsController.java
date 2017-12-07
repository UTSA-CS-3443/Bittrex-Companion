package controller;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import javafx.scene.control.Label;
import model.Coin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

/**
 * Controller for the coin detail screen 
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */

public class OppsController {
	
	private static final int BUY_SELL_SCREEN_HEIGHT = 400;
	private static final int BUY_SELL_SCREEN_WIDTH = 600;
	
	@FXML private Label nameLabel;
	@FXML private Label highLabel;
	@FXML private Label lowLabel;
	@FXML private Label volumeLabel;
	@FXML private Label lastLabel;
	@FXML private Label bidLabel;
	@FXML private Label askLabel;
	@FXML private Label buyLabel;
	@FXML private Label sellLabel;
	@FXML private Button buyButton;
	@FXML private Button sellButton;
	
	private String[] coinStrings = {"coin1", "coin2", "coin3", "coin4", "coin5", "coin6", "coin7", "coin8", "coin9", "coin10", "coin11", ""};
	private String[] tempSplitArray;
	private HashMap<String, String> coinsMap;
	private String readStr;
	private File inFile;
	private Scanner fileIn;
	private String coinString, nameString;
	private Coin coin;
	private String balance;
	
	/**
	 * Method to run on initialization of the screen 
	 * 
	 * @param tempCoinName coin details to load
	 */
	public void initialize(String tempCoinName, String balance) {
		this.balance = balance;
		this.coinString = tempCoinName;
		try {
			coinsMap = new HashMap<String, String>();
			inFile = new File("TableCoins.txt");
			fileIn = new Scanner(inFile);
			for (int i = 0; i < 11; i++) {
				readStr = fileIn.next();
				tempSplitArray = readStr.split(",");
				nameString = tempSplitArray[0];
				nameString = nameString.replace('"', ' ');
				nameString.trim();
				tempSplitArray = nameString.split("-");
				nameString = tempSplitArray[1].trim();
				coinsMap.put(this.coinStrings[i], nameString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		populate();	
	}
	/**
	 * Set the coin to be used
	 * 
	 * @param coinVal name of the coin
	 */
	public void setCoinVal(String coinVal) {
		this.coinString = coinVal;
		System.out.println(coinVal);
	}
	/**
	 * Set the labels to the proper values
	 */
	private void populate() {
		this.coin = new Coin(coinsMap.get(this.coinString), "", "");
		nameLabel.setText(this.coin.getMarketSummary("MarketName"));
		highLabel.setText(this.coin.getMarketSummary("High"));
		lowLabel.setText(this.coin.getMarketSummary("Low"));
		volumeLabel.setText(this.coin.getMarketSummary("Volume"));
		lastLabel.setText(this.coin.getMarketSummary("Last"));
		bidLabel.setText(this.coin.getMarketSummary("Bid"));
		askLabel.setText(this.coin.getMarketSummary("Ask"));
		buyLabel.setText(this.coin.getMarketSummary("OpenBuyOrders"));
		sellLabel.setText(this.coin.getMarketSummary("OpenSellOrders"));
	}
	/**
	 * Method to open the buy screen
	 * @param event Triggered on click
	 */
	public void OpenBuy(ActionEvent event) {
		try {
			Stage current = (Stage) this.buyButton.getScene().getWindow();
			current.setTitle("Buy");
			FXMLLoader loader = new FXMLLoader(new File("src/view/Buy.fxml").getAbsoluteFile().toURI().toURL());
			Parent root = loader.load();
			BuyController buyControl = loader.getController();
			buyControl.passCoin(this.nameLabel.getText(), Double.valueOf(this.balance), this.coin.getMarketSummary("ask"));
			Scene scene = new Scene(root, BUY_SELL_SCREEN_WIDTH, BUY_SELL_SCREEN_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			current.setScene(scene);
			current.show();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Method to open the sell screen
	 * @param event Triggered on click
	 */
	public void OpenSell(ActionEvent event) {
		try {
			Stage current = (Stage) this.buyButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(new File("src/view/Sell.fxml").getAbsoluteFile().toURI().toURL());
			Parent root = loader.load();
			SellController sellControl = loader.getController();
			sellControl.passCoin(this.nameLabel.getText(), Double.valueOf(this.balance), this.coin.getMarketSummary("bid"));
			Scene scene = new Scene(root, BUY_SELL_SCREEN_WIDTH , BUY_SELL_SCREEN_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			current.setScene(scene);
			current.show();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
