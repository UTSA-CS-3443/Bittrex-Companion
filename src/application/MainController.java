package application;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import apis.Coin;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class MainController {
	private String balance, name, apiKey;
	private Coin coin;
	@FXML
	private Label balanceLabel;
	@FXML
	private Label nameLabel;
	
	public void initialize() {
	/*	try {
			Scanner readIn = new Scanner(new File("UserInfo.txt"));
			this.name = readIn.next();
			this.apiKey = readIn.next();
			this.coin = new Coin("LTC", this.apiKey);
			readIn.close();
			this.balance = this.coin.getBalance();
		} catch (FileNotFoundException e) {
			// Should never happen
			// TODO: Figure out some logic to put here just in case
		}
		this.balanceLabel.setText(this.balance);
		*/
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
