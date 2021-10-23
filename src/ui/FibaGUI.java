package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Fiba;
import model.Player;

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
    private TableView<Player> tvOfPlayers;

    @FXML
    private TableColumn<Player, String> tcName;

    @FXML
    private TableColumn<Player, Integer> tcAge;

    @FXML
    private TableColumn<Player, Integer> tcTeam;

    @FXML
    private TableColumn<Player, Integer> tcPoints;

    @FXML
    private TableColumn<Player, Integer> tcBounces;

    @FXML
    private TableColumn<Player, Integer> tcAssists;

    @FXML
    private TableColumn<Player, Integer> tcSteals;

    @FXML
    private TableColumn<Player, Integer> tcBlocks;
    
    public FibaGUI(Fiba f) {
    	fiba = f;
    }
    
    public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WelcomePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/logo.jpg)");
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
    public void btImportPlayers(ActionEvent event) {

    }

    @FXML
    public void btManagePlayers(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/manage-player.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/fondo.jpg)");
		initializeTableViewOfAddedPlayers();
    }

    private void initializeTableViewOfAddedPlayers() {
    	ObservableList<Player> observableList;
		observableList = FXCollections.observableArrayList(fiba.getPlayers());
		tvOfPlayers.setItems(observableList);
		tcName.setCellValueFactory(new PropertyValueFactory<Player, String>("Name"));
		tcAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
		tcTeam.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Team"));
		tcPoints.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Points"));
		tcBounces.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Bounces"));
		tcAssists.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Assists"));
		tcSteals.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Steals"));
		tcBlocks.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Blocks"));
	}

	@FXML
    public void btSearchPlayers(ActionEvent event) {

    }
}
