package application;
import apis.Coin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Main extends Application {
	
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
		fileInitialize();
		File writeFile = new File("TableCoins.txt");
		PrintWriter out = new PrintWriter(writeFile);
		int count = 0;
		String[] coinNamesToAnalyze = {"BCC", "ETH", "BTG", "NEO", "TIX", "EMC2", "LTC", "ETC", "OMG", "POWR", "ZEC",
				"DASH", "XRP", "STRAT", "TRIG", "RCN", "LSK", "SYS", "NXT", "VTC"};
		Coin[] coinsToAnalyze = new Coin[20];
		
		for (int i = 0; i < 20; i++) 
			coinsToAnalyze[i] = new Coin(coinNamesToAnalyze[i], "", "");
		Analyzer coinAnalyzer;
		for (int i = 0; i < 20; i++) {
			coinAnalyzer = new Analyzer(coinsToAnalyze[i]);
			if (coinAnalyzer.checkForSuccess() && count < 11) {
				out.printf("%s,%f,%f,%f,%.2f\n",coinAnalyzer.getName(), coinAnalyzer.getValue(), coinAnalyzer.getHigh(),
						coinAnalyzer.getLow(), coinAnalyzer.getPChange());
				out.flush();
				count++;
			}
		}
		out.close();
		launch(args);
	}
	
	private static void fileInitialize() {
		File writeFile = new File("TableCoins.txt");
		if (writeFile.exists() == false) 
			try {
				writeFile.createNewFile();
			} catch (IOException e) {
				//HAHA Nothing happens here
			}
	}
}
