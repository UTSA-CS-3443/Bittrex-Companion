package application;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import apis.Coin;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OppsController {
	@FXML
	private Label nameLabel;
	@FXML
	private Label highLabel;
	@FXML
	private Label lowLabel;
	@FXML 
	private Label volumeLabel;
	@FXML
	private Label lastLabel;
	@FXML
	private Label bidLabel;
	@FXML 
	private Label askLabel;
	@FXML
	private Label buyLabel;
	@FXML 
	private Label sellLabel;
	@FXML 
	private Button buyButton;
	@FXML
	private Button sellButton;
	
	private String[] coinStrings = {"coin1", "coin2", "coin3", "coin4", "coin5", "coin6", "coin7", "coin8", "coin9", "coin10", "coin11", ""};
	private String[] tempSplitArray;
	private HashMap<String, String> coinsMap;
	private String readStr;
	private File inFile;
	private Scanner fileIn;
	private String coinString, nameString;
	private Coin coin;
	
	
	public void initialize(String tempCoinName) {
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
	
	public void setCoinVal(String coinVal) {
		this.coinString = coinVal;
		System.out.println(coinVal);
	}
	
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

}
