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

public class LoginController {

	private static final int cryptVal = 45;
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
	private Button regButton;
	@FXML 
	private Button logButton;
	
	private Stage primaryStage;
	private Parent root;

	
	public void Login(ActionEvent event) throws Exception{
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
				Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				lblStatus.setText("Login Success");
				Scene scene = new Scene(root,900,700);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				} else {
					lblStatus.setText("Login Failed");
				}
			}
	}
	
	public void Register(ActionEvent event) throws Exception {
		Stage current = (Stage) logButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		current.setScene(scene);
		current.show();
		
	}
	
	public void CreateUser(ActionEvent event) throws Exception {
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
			output.write(regName.getText() + "\n" + regAPIKey.getText());
			output.close();
			regStatus.setText("Registration Succesful!");
			Stage current = (Stage) regButton.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			current.setScene(scene);
			current.show();
		}
		else {
			regStatus.setText("Passwords do not match!");
		}	
	}
	
}
