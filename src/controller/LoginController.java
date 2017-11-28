package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

/**
 * Class to do all the logic for the Login Screen
 * 
 * @author Hunter Jones
 * @author Jerome Stowe
 * @author Oscar Tena
 * @author Micheal Womack	
 * @author Richard Amareth
 *
 */

public class LoginController {

	private String username, password;
	
	@FXML
	private Label lblStatus;
	@FXML 
	private Label regStatus;
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;
	@FXML 
	private TextField regUsername;
	@FXML
	private TextField regPassword;
	@FXML
	private TextField regPasswordConfirm;
	@FXML
	private TextField regName;
	@FXML
	private TextField regAPIKey;
	@FXML
	private TextField regAPISecret;
	@FXML
	private Button regButton;
	@FXML 
	private Button logButton;
	
	private Parent root;

	/**
	 * Checks for login information and compares with registered info
	 * @param event 
	 * @throws Exception 
	 */
	public void Login(ActionEvent event) throws IOException, FileNotFoundException {
		File infile = new File("UserStore.txt");
		if (infile.exists()) {
			Scanner scan = new Scanner(infile);
			while (scan.hasNext()) {
				this.username = scan.next();
				this.password = scan.next();
			}
			scan.close();
		}
		else {
			lblStatus.setText("None Registered");
		}
		
		if (this.username != null && this.password != null) {
			if(txtUsername.getText().equals(this.username) && txtPassword.getText().equals(this.password)) {
				Stage current = (Stage) logButton.getScene().getWindow();
				current.close();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
				lblStatus.setText("Login Success");
				Scene scene = new Scene(root,900,700);
				scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				} else {
					lblStatus.setText("Login Failed");
				}
			}
	}
	
	/**
	 * Displays the registration screen
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void Register(ActionEvent event) throws IOException {
		Stage current = (Stage) logButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
		current.setScene(scene);
		current.show();
		
	}
	/**
	 * Creates user based on registration screen info
	 * @param event
	 * @throws IOException
	 */
	public void CreateUser(ActionEvent event) throws IOException {
		if (regPassword.getText().equals(regPasswordConfirm.getText()) && regPassword.getText() != null) {
			File outfile = new File("UserStore.txt");
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			FileWriter output = new FileWriter(outfile);
			output.write(regUsername.getText() + "\n" + regPassword.getText());
			output.close();
			outfile = new File("UserInfo.txt");
			if (!outfile.exists()) {
				outfile.createNewFile();
			}
			output = new FileWriter(outfile);
			output.write(regName.getText() + "\n" + regAPIKey.getText() + "\n" + regAPISecret.getText());
			output.close();
			regStatus.setText("Registration Succesful!");
			Stage current = (Stage) regButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			current.setScene(scene);
			current.show();
		}
		else {
			regStatus.setText("Passwords do not match!");
		}	
	}
	
}
