package application;
import java.io.File;
import java.io.PrintWriter;
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

public class MainController {

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
	private Button regButton;
	@FXML 
	private Button logButton;
	
	
	public void Login(ActionEvent event) throws Exception{
		File infile = new File("UserStore.txt");
		if (infile.exists()) {
			Scanner scan = new Scanner(infile);
			this.username = scan.next();
			this.password = scan.next();
		}
		else {
			lblStatus.setText("Not Registered");
		}
		if (this.username != null && this.password != null) {
			if(txtUsername.getText().equals(this.username) && txtPassword.getText().equals(this.password)) {
				Stage current = (Stage) logButton.getScene().getWindow();
				current.close();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				lblStatus.setText("Login Success");
				Scene scene = new Scene(root,800,800);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				} else {
					lblStatus.setText("Login Failed");
				}
			}
	}
	
	public void Register(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void CreateUser(ActionEvent event) throws Exception {
		System.out.println(regPassword.getText().equals(regPasswordConfirm.getText()));
		if (regPassword.getText().equals(regPasswordConfirm.getText())) {
			File outfile = new File("UserStore.txt");
			outfile.createNewFile();
			PrintWriter output = new PrintWriter(outfile);
			output.println(regUsername.getText());
			output.print(regPassword.getText());
			output.close();
			regStatus.setText("Registration Succesful!");
			Stage current = (Stage) regButton.getScene().getWindow();
			current.close();
		}
		else {
			regStatus.setText("Passwords do not match!");
		}
		
	}
}
