package bittrexCompanionDataTable;
/**
 * @author oscar Tena
 * 
 * This class launches the DataTable for the Bittrex Companion Application
 * Currently it has a bug in the display of the pChange Value.  
 *
 */
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<Table> table;

    public static void main(String[] args) {
        launch(args);
    }

   
    @Override
	public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Bittrex Companion");

        //coin column
        
        TableColumn<Table, String> coinColumn = new TableColumn<>("coin");
        coinColumn.setMinWidth(200);
        coinColumn.setCellValueFactory(new PropertyValueFactory<>("coin"));

        //volume column
        
        TableColumn<Table, Double> volumeColumn = new TableColumn<>("volume");
        volumeColumn.setMinWidth(200);
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

        //high column
        
        TableColumn<Table, String> highColumn = new TableColumn<>("high");
        highColumn.setMinWidth(200);
        highColumn.setCellValueFactory(new PropertyValueFactory<>("high"));

        // low column
        
        TableColumn<Table, String> lowColumn = new TableColumn<>("low");
        lowColumn.setMinWidth(200);
        lowColumn.setCellValueFactory(new PropertyValueFactory<>("low"));

        // pchange column
        
        TableColumn<Table, String> pChangeColumn = new TableColumn<>("percent change");
        pChangeColumn.setMinWidth(200);
        pChangeColumn.setCellValueFactory(new PropertyValueFactory<>("percent change"));

        
        
        table = new TableView<>();
        table.setItems(getTable());
        table.getColumns().addAll(coinColumn, volumeColumn, highColumn,lowColumn,pChangeColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    /**
     * GetTable creates an ObservableList object  with type<Table> and returns it back.
     * this will likely be changed since we wont be updating values manually.
     * this is only for display purposes.
     */
	public ObservableList<Table> getTable(){
        ObservableList<Table> table = FXCollections.observableArrayList();
        ((List<Table>) table).add(new Table("gold",1,1,1,1));
        
        return table;
    }


}