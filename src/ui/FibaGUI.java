package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import model.Fiba;

public class FibaGUI {
	
	private Fiba fiba;
	
	@FXML
	private BorderPane mainPane;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtTeam;

    @FXML
    private TextField txtPoints;

    @FXML
    private TextField txtBounces;

    @FXML
    private TextField txtAssists;

    @FXML
    private TextField txtSteals;

    @FXML
    private TextField txtBlocks;

    @FXML
    private TableView<?> tvOfPlayers;

    @FXML
    private TableColumn<?, ?> tcName;

    @FXML
    private TableColumn<?, ?> tcAge;

    @FXML
    private TableColumn<?, ?> tcTeam;

    @FXML
    private TableColumn<?, ?> tcPoints;

    @FXML
    private TableColumn<?, ?> tcBounces;

    @FXML
    private TableColumn<?, ?> tcAssists;

    @FXML
    private TableColumn<?, ?> tcSteals;

    @FXML
    private TableColumn<?, ?> tcBlocks;
    
    public FibaGUI(Fiba f) {
    	fiba = f;
    }

    @FXML
    public void addPlayer(ActionEvent event) {

    }

    @FXML
    public void deletePlayer(ActionEvent event) {

    }

    @FXML
    public void returnToMenu(ActionEvent event) throws IOException {
    	showWelcomeWindow();
    }

    @FXML
    public void updatePlayer(ActionEvent event) {

    }
    
    @FXML
    public void btExitProgram(ActionEvent event) {

    }

    @FXML
    public void btImportPlayers(ActionEvent event) {

    }

    @FXML
    public void btManagePlayers(ActionEvent event) {

    }

    @FXML
    public void btSearchPlayers(ActionEvent event) {

    }

	public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WelcomePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo1.jpg)");
	}
}
