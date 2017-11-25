package application;
import java.io.File;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.event.ActionEvent;
import apis.Coin;
import java.io.FileNotFoundException;



public class MainController {
	private String balance, name, apiKey, apiSecret;
	private Coin coin;
	@FXML
	private Label balanceLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private TextField coin1;
	@FXML 
	private TextField coin2;
	@FXML 
	private TextField coin3;
	@FXML 
	private TextField coin4;
	@FXML
	private TextField coin5;
	@FXML
	private TextField coin6;
	@FXML
	private TextField coin7;
	@FXML
	private TextField coin8;
	@FXML
	private TextField coin9;
	@FXML
	private TextField coin10;
	@FXML
	private TextField coin11;
	@FXML 
	private TextField value1;
	@FXML 
	private TextField value2;
	@FXML 
	private TextField value3;
	@FXML 
	private TextField value4;
	@FXML 
	private TextField value5;
	@FXML 
	private TextField value6;
	@FXML 
	private TextField value7;
	@FXML 
	private TextField value8;
	@FXML 
	private TextField value9;
	@FXML 
	private TextField value10;
	@FXML 
	private TextField value11;
	@FXML 
	private TextField high1;
	@FXML 
	private TextField high2;
	@FXML 
	private TextField high3;
	@FXML 
	private TextField high4;
	@FXML 
	private TextField high5;
	@FXML 
	private TextField high6;
	@FXML 
	private TextField high7;
	@FXML 
	private TextField high8;
	@FXML 
	private TextField high9;
	@FXML 
	private TextField high10;
	@FXML 
	private TextField high11;
	@FXML 
	private TextField low1;
	@FXML 
	private TextField low2;
	@FXML 
	private TextField low3;
	@FXML 
	private TextField low4;
	@FXML 
	private TextField low5;
	@FXML 
	private TextField low6;
	@FXML 
	private TextField low7;
	@FXML 
	private TextField low8;
	@FXML 
	private TextField low9;
	@FXML 
	private TextField low10;
	@FXML 
	private TextField low11;
	@FXML 
	private TextField pChange1;
	@FXML 
	private TextField pChange2;
	@FXML 
	private TextField pChange3;
	@FXML 
	private TextField pChange4;
	@FXML 
	private TextField pChange5;
	@FXML 
	private TextField pChange6;
	@FXML 
	private TextField pChange7;
	@FXML 
	private TextField pChange8;
	@FXML 
	private TextField pChange9;
	@FXML 
	private TextField pChange10;
	@FXML 
	private TextField pChange11;
	//micheal & Jerome---test
	//@FXML private WebView webView;
	//@FXML private WebEngine engine;
	
	
	
	public void initialize() {
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
			
			//start jerome webview
			//engine = webView.getEngine();
			//engine.load("http://www.purple.com");
			//end Jerome
			
		} catch (FileNotFoundException e) {
			// Should never happen
			// TODO: Figure out some logic to put here just in case
		}
		//this.balanceLabel.setText(this.balance);
		//replacing above with below for demo
		this.balanceLabel.setText("3.52 BTC");
		this.nameLabel.setText(this.name);
		//engine.load("http://www.google.com");

		
	}
	
	public void populateTable(int i, String coinString) {
		String coinName, value, high, low, pChange;
		String[] splitString;
		splitString = coinString.split(",");
		coinName = splitString[0];
		value = splitString[1];
		high = splitString[2];
		low = splitString[3];
		pChange = splitString[4];

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
	
	public void OpenOpps() {
		try {
			Stage oppStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Opps.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			oppStage.setScene(scene);
			oppStage.show();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	
}
