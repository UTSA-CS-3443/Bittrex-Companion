package application;
import apis.Coin;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.util.Date;


public class Main extends Application {
	//set this to true to run the below code in main
	public static final boolean TEST = false;
	
	//set this to true and set the int value to the number of seconds to wait between pulls 
	public static final boolean GATHER_DATA = true;
	public static final long TIME_TO_WAIT_IN_SECONDS = 10;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//If you want this to run, set TEST above to true
		if (TEST) {
			String[] coins = {"ETH", "NEO", "VTC", "BCC", "OMG"};
			String[][] totalVals = new String[5][4];
			for (int i = 0; i < 5; i++) {
				Coin newCoin = new Coin(coins[i], "", "");
				totalVals[i][0] = newCoin.getMarketSummary("High");
				totalVals[i][1] = newCoin.getMarketSummary("Low");
				totalVals[i][2] = newCoin.getMarketSummary("Volume");
				totalVals[i][3] = newCoin.getMarketSummary("PrevDay");
				System.out.println("Coin: " + coins[i]);
				System.out.println("\tHigh: " + totalVals[i][0]);
				System.out.println("\tLow: " + totalVals[i][1]);
				System.out.println("\tVolume: " + totalVals[i][0]);
				System.out.println("\tPrevious Day: " + totalVals[i][0]);
			}
		}
		
		if (GATHER_DATA) {
			String[] coins = {"ETH", "NEO", "BCC", "VTC", "ADA", "OMG", "XRP", "LTC", "LSK", "GRS"};
			String temp;
			String[] split = new String[2];
			String[][] coinVals = new String[10][2];
			File outFile = new File("TestData.txt");
			PrintWriter out = new PrintWriter(outFile);
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			while (true) {
				for (int i = 0; i < 10; i++) {
					Coin tempCoin = new Coin(coins[i], "", "");
					temp = tempCoin.getMarketSummary("TimeStamp");
					split = temp.split("T");
					split[1] = split[1].replace('"', ' ').trim();
					split[0] = tempCoin.getTicker("Bid");
					coinVals[i][0] = split[1];
					coinVals[i][1] = split[0];
					out.println(coins[i] + "," + coinVals[i][0] + "," + coinVals[i][1]);
				}
				out.println();
				out.flush();
				Thread.sleep(TIME_TO_WAIT_IN_SECONDS * 1000);
			}
		}
		
		
		
		//launch(args);
	}
}
