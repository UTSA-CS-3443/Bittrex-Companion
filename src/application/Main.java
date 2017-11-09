package application;
import apis.Coin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Date;


public class Main extends Application {
	//set this to true to run the below code in main
	public static final boolean TEST = false;
	
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
		Coin testCoin = new Coin("LTC", "", "");
		String hello;
		while (true) {
			Thread.sleep(10000);
			testCoin = new Coin("LTC", "", "");
			hello = testCoin.getMarketSummary("TimeStamp");
			System.out.println(hello);

		}
		
		
		//launch(args);
	}
}
