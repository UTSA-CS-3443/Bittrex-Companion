package application;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import apis.Coin;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;
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
	private String[] coinNameStrings;
	private String[] tempSplitArray;
	private HashMap<String, String> coinsMap;
	private String readStr;
	private File inFile;
	private Scanner fileIn;
	private String coinString, nameString;
	private Coin coin;
	private Stage currentStage;
	
	
	public void initialize() {
		coinNameStrings = new String[12];
		try {
			currentStage = (Stage) buyButton.getScene().getWindow();
			this.coinString = currentStage.getTitle();
			coinsMap = new HashMap<String, String>();
			inFile = new File("TableCoins.txt");
			fileIn = new Scanner(inFile);
			for (int i = 0; i < 12; i++) {
				readStr = fileIn.next();
				tempSplitArray = readStr.split(",");
				nameString = tempSplitArray[0];
				nameString = nameString.replace('"', ' ');
				nameString.trim();
				System.out.println(nameString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		populateFields();
	}
	
	private void populateFields() {
		
	}
}
