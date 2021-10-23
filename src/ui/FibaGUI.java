package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Importante");
        alert.setContentText("Esta accion puede tomar unos minutos...");
        alert.showAndWait();
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Abrir el archivo");
    	File f=fileChooser.showOpenDialog(mainPane.getScene().getWindow());
    	if(f!=null) {
            alert.setTitle("Importar clientes");
            try {
                fiba.importPlayersData(f.getAbsolutePath());
                alert.setContentText("Los clientes fueron importados exitosamente");
                alert.showAndWait();
            }catch(IOException e){
                alert.setContentText("Los clientes no se importaron. Ocurrió un error");
                alert.showAndWait();
            }
    	}
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
