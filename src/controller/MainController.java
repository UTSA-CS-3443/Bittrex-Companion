package controller;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Coin;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.FileNotFoundException;
import model.TripleList;
import java.util.ArrayList;

/**
 * Class to handle all the logic for the Main Screen
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */
public class MainController {
	
	private static final String WEBSITE_STRING = "http://www.helenpetrystoweforjudge.com/cs/twitterfeed.php";
	private static final int COIN_SCREEN_HEIGHT = 400;
	private static final int COIN_SCREEN_WIDTH = 600;
	

	private String balance, name, apiKey, apiSecret;
	private Coin coin;
	private ArrayList<TripleList> topCoinList;
	private double volumeArray[];
	
	@FXML private Label balanceLabel;
	@FXML private Label nameLabel;
	@FXML private TextField coin1;
	@FXML private TextField coin2;
	@FXML private TextField coin3;
	@FXML private TextField coin4;
	@FXML private TextField coin5;
	@FXML private TextField coin6;
	@FXML private TextField coin7;
	@FXML private TextField coin8;
	@FXML private TextField coin9;
	@FXML private TextField coin10;
	@FXML private TextField coin11;
	@FXML private TextField value1;
	@FXML private TextField value2;
	@FXML private TextField value3;
	@FXML private TextField value4;
	@FXML private TextField value5;
	@FXML private TextField value6;
	@FXML private TextField value7;
	@FXML private TextField value8;
	@FXML private TextField value9;
	@FXML private TextField value10;
	@FXML private TextField value11;
	@FXML private TextField high1;
	@FXML private TextField high2;
	@FXML private TextField high3;
	@FXML private TextField high4;
	@FXML private TextField high5;
	@FXML private TextField high6;
	@FXML private TextField high7;
	@FXML private TextField high8;
	@FXML private TextField high9;
	@FXML private TextField high10;
	@FXML private TextField high11;
	@FXML private TextField low1;
	@FXML private TextField low2;
	@FXML private TextField low3;
	@FXML private TextField low4;
	@FXML private TextField low5;
	@FXML private TextField low6;
	@FXML private TextField low7;
	@FXML private TextField low8;
	@FXML private TextField low9;
	@FXML private TextField low10;
	@FXML private TextField low11;
	@FXML private TextField pChange1;
	@FXML private TextField pChange2;
	@FXML private TextField pChange3;
	@FXML private TextField pChange4;
	@FXML private TextField pChange5;
	@FXML private TextField pChange6;
	@FXML private TextField pChange7;
	@FXML private TextField pChange8;
	@FXML private TextField pChange9;
	@FXML private TextField pChange10;
	@FXML private TextField pChange11;
	@FXML private TextField topCoin1;
	@FXML private TextField topCoin2;
	@FXML private TextField topCoin3;
	@FXML private TextField topVol1;
	@FXML private TextField topVol2;
	@FXML private TextField topVol3;
	@FXML private TextField topPChange1;
	@FXML private TextField topPChange2;
	@FXML private TextField topPChange3;
	@FXML private WebView view;
	@FXML private WebEngine engine;
	
	
	/** 
	 * Initial setup when screen launches
	 */
	public void initialize() {
		this.volumeArray = new double[15];
		this.topCoinList = new ArrayList<TripleList>();
		this.balance = null;
		engine = view.getEngine();
		engine.load(WEBSITE_STRING);

		try {
			Scanner readIn = new Scanner(new File("UserInfo.txt"));
			this.name = readIn.next();
			this.apiKey = readIn.next();
			this.apiSecret = readIn.next();
			this.coin = new Coin("BTC", this.apiKey, this.apiSecret);
			readIn.close();
			readIn = new Scanner(new File("TableCoins.txt"));
			int count = 0;
			while (readIn.hasNext()) {
				populateTable(count, readIn.next());
				count++;
			}
			readIn.close();
			this.balance = "Balance = " + this.coin.getBalance() + " Bitcoin";
		} catch (FileNotFoundException e) {
			// Should never happen
		}
		this.balanceLabel.setText(this.balance);
		this.nameLabel.setText(this.name);
		populateTopCoin();
	}
	/**
	 * Adds the data to the table
	 * 
	 * @param i position on the table to add the data to
	 * @param coinString The value to add to the table in that position
	 */
	public void populateTable(int i, String coinString) {
		String coinName, value, high, low, pChange;
		String[] splitString;
		splitString = coinString.split(",");
		coinName = splitString[0];
		value = splitString[1];
		high = splitString[2];
		low = splitString[3];
		pChange = splitString[4];
		this.volumeArray[i] = Double.valueOf(splitString[5]);

		switch (i) {
		case 0:
			this.coin1.setText(coinName);
			this.value1.setText(value);
			this.high1.setText(high);
			this.low1.setText(low);
			this.pChange1.setText(pChange);
		case 1:
			this.coin2.setText(coinName);
			this.value2.setText(value);
			this.high2.setText(high);
			this.low2.setText(low);
			this.pChange2.setText(pChange);
		case 2:
			this.coin3.setText(coinName);
			this.value3.setText(value);
			this.high3.setText(high);
			this.low3.setText(low);
			this.pChange3.setText(pChange);
		case 3:
			this.coin4.setText(coinName);
			this.value4.setText(value);
			this.high4.setText(high);
			this.low4.setText(low);
			this.pChange4.setText(pChange);
		case 4:
			this.coin5.setText(coinName);
			this.value5.setText(value);
			this.high5.setText(high);
			this.low5.setText(low);
			this.pChange5.setText(pChange);
		case 5:
			this.coin6.setText(coinName);
			this.value6.setText(value);
			this.high6.setText(high);
			this.low6.setText(low);
			this.pChange6.setText(pChange);
		case 6:
			this.coin7.setText(coinName);
			this.value7.setText(value);
			this.high7.setText(high);
			this.low7.setText(low);
			this.pChange7.setText(pChange);
		case 7:
			this.coin8.setText(coinName);
			this.value8.setText(value);
			this.high8.setText(high);
			this.low8.setText(low);
			this.pChange8.setText(pChange);
		case 8:
			this.coin9.setText(coinName);
			this.value9.setText(value);
			this.high9.setText(high);
			this.low9.setText(low);
			this.pChange9.setText(pChange);
		case 9:
			this.coin10.setText(coinName);
			this.value10.setText(value);
			this.high10.setText(high);
			this.low10.setText(low);
			this.pChange10.setText(pChange);
		case 10:
			this.coin11.setText(coinName);
			this.value11.setText(value);
			this.high11.setText(high);
			this.low11.setText(low);
			this.pChange11.setText(pChange);
		}		
	}
	
	/**
	 * Opens the screen for coin details
	 * 
	 * @param event
	 */
	public void OpenOpps(MouseEvent event) {
		String passString;
		try {
			Stage oppStage = new Stage();
			FXMLLoader loader = new FXMLLoader(new File("src/view/Opps.fxml").getAbsoluteFile().toURI().toURL());
			Parent root = loader.load();
			oppStage.setTitle("Coin Detail");
			Node source = (Node)event.getSource();
			passString = source.getId();
			Scene scene = new Scene(root,COIN_SCREEN_WIDTH, COIN_SCREEN_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			OppsController controller = loader.getController();
			controller.initialize(passString, this.coin.getBalance());
			oppStage.setScene(scene);
			oppStage.show();				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to populate the table of coins with best volume
	 */
	private void populateTopCoin() {
		topCoinList.add(new TripleList(this.coin1.getText(), this.volumeArray[0], this.pChange1.getText()));
		topCoinList.add(new TripleList(this.coin2.getText(), this.volumeArray[1], this.pChange2.getText()));
		topCoinList.add(new TripleList(this.coin3.getText(), this.volumeArray[2], this.pChange3.getText()));
		topCoinList.add(new TripleList(this.coin4.getText(), this.volumeArray[3], this.pChange4.getText()));
		topCoinList.add(new TripleList(this.coin5.getText(), this.volumeArray[4], this.pChange5.getText()));
		topCoinList.add(new TripleList(this.coin6.getText(), this.volumeArray[5], this.pChange6.getText()));
		topCoinList.add(new TripleList(this.coin7.getText(), this.volumeArray[6], this.pChange7.getText()));
		topCoinList.add(new TripleList(this.coin8.getText(), this.volumeArray[7], this.pChange8.getText()));
		topCoinList.add(new TripleList(this.coin9.getText(), this.volumeArray[8], this.pChange9.getText()));
		topCoinList.add(new TripleList(this.coin10.getText(), this.volumeArray[9] , this.pChange10.getText()));
		topCoinList.add(new TripleList(this.coin11.getText(), this.volumeArray[10] , this.pChange11.getText()));
		Collections.sort(topCoinList);
		topCoin1.setText(topCoinList.get(2).getName());
		topCoin2.setText(topCoinList.get(1).getName());
		topCoin3.setText(topCoinList.get(0).getName());
		topVol1.setText(String.valueOf(topCoinList.get(2).getVolume()));
		topVol2.setText(String.valueOf(topCoinList.get(1).getVolume()));
		topVol3.setText(String.valueOf(topCoinList.get(0).getVolume()));
		topPChange1.setText(topCoinList.get(2).getPChange());
		topPChange2.setText(topCoinList.get(1).getPChange());
		topPChange3.setText(topCoinList.get(0).getPChange());
	}
}
