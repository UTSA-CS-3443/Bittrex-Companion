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
	private TextField pChange1;
	@FXML 
	private TextField pChange2;
	@FXML 
	private TextField pChange3;
	@FXML 
	private TextField pChange4;
	@FXML 
	private TextField pChange5;
	//micheal & Jerome---test

	
	public void initialize() {
		try {
			Scanner readIn = new Scanner(new File("UserInfo.txt"));
			this.name = readIn.next();
			this.apiKey = readIn.next();
			this.apiSecret = readIn.next();
			this.coin = new Coin("BTC", this.apiKey, this.apiSecret);
			readIn.close();
			this.balance = "Balance = " + this.coin.getBalance() + " Bitcoin";		
		} catch (FileNotFoundException e) {
			// Should never happen
			// TODO: Figure out some logic to put here just in case
		}
		this.balanceLabel.setText(this.balance);
		this.nameLabel.setText(this.name);
		

		
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
