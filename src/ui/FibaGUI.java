package ui;

import java.io.File;
import java.io.IOException;

import exceptions.NegativeValueException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
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
	private TableColumn<Player, String> tcTeam;

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

	@FXML
	private RadioButton rbPoints;

	@FXML
	private ToggleGroup searchCriteria;

	@FXML
	private RadioButton rbSteals;

	@FXML
	private RadioButton rbAssists;

	@FXML
	private RadioButton rbBlocks;

	@FXML
	private RadioButton rbRebounds;

	@FXML
	private TextField txtValue;

	@FXML
	private RadioButton rbEqual;

	@FXML
	private ToggleGroup comparison;

	@FXML
	private RadioButton rbGreater;

	@FXML
	private RadioButton rbLess;

	@FXML
	private TableView<Player> tvSearchedPlayers;

	@FXML
	private TableColumn<Player, String> colName;

	@FXML
	private TableColumn<Player, Integer> colAge;

	@FXML
	private TableColumn<Player, String> colTeam;

	@FXML
	private TableColumn<Player, Integer> colPoints;

	@FXML
	private TableColumn<Player, Integer> colRebounds;

	@FXML
	private TableColumn<Player, Integer> colAssists;

	@FXML
	private TableColumn<Player, Integer> colSteals;

	@FXML
	private TableColumn<Player, Integer> colBlocks;


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
		if(!txtName.getText().equals("") && !txtAge.getText().equals("") && !txtTeam.getText().equals("") && !txtPoints.getText().equals("") && !txtBounces.getText().equals("") && !txtAssists.getText().equals("") && !txtSteals.getText().equals("") && !txtBlocks.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				fiba.addPlayer(txtName.getText(), txtAge.getText(), txtTeam.getText(), txtPoints.getText(), txtBounces.getText(), txtAssists.getText(), txtSteals.getText(), txtBlocks.getText());
				initializeTableViewOfAddedPlayers();
				txtName.clear();
				txtAge.clear();
				txtTeam.clear();
				txtPoints.clear();
				txtBounces.clear();
				txtAssists.clear();
				txtSteals.clear();
				txtBlocks.clear();
			}catch(NumberFormatException num) {
				alert1.setContentText("Debe ingresar un numero dentro de los campos presentados que asi lo requieran");
				alert1.showAndWait();
			}catch(NegativeValueException value) {
				alert1.setContentText(value.getMessage());
				alert1.showAndWait();
			}
		}else {
			showValidationErrorAlert();
		}
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
                		alert.setContentText("Los jugadores fueron importados exitosamente");
                		alert.showAndWait();
            		}catch(IOException e){
                		alert.setContentText("Los jugadores no se importaron. Ocurrió un error");
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
		tcTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("Team"));
		tcPoints.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Points"));
		tcBounces.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Bounces"));
		tcAssists.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Assists"));
		tcSteals.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Steals"));
		tcBlocks.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Blocks"));
	}

	@FXML
	public void btSearchPlayers(ActionEvent event) {

	}


	private void initializeTableViewSearchedPlayers() {
		ObservableList<Player> observableList;
		observableList = FXCollections.observableArrayList(fiba.getPlayers()); ///to change: get the players searched
		tvOfPlayers.setItems(observableList);
		colName.setCellValueFactory(new PropertyValueFactory<Player, String>("Name"));
		colAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
		colTeam.setCellValueFactory(new PropertyValueFactory<Player, String>("Team"));
		colPoints.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Points"));
		colRebounds.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Bounces"));
		colAssists.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Assists"));
		colSteals.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Steals"));
		colBlocks.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Blocks"));
	}


	@FXML
	public void searchPlayers(ActionEvent event) {
		if(searchCriteria.getSelectedToggle()!=null && comparison.getSelectedToggle()!=null && !txtValue.getText().isEmpty()) {
			initializeTableViewSearchedPlayers();
			//searchCriteria.getSelectedToggle().setSelected(false);
			//comparison.getSelectedToggle().setSelected(false);

		}else {
			showValidationErrorAlert();
		}
	}

	public String getSearchCriteria() {
		String criteria = "";
		if(rbPoints.isSelected()) {
			criteria = "POINTS";
		} 
		else if (rbSteals.isSelected()) {
			criteria = "STEALS";
		} else if (rbAssists.isSelected()) {
			criteria = "ASSISTS";
		}else if (rbBlocks.isSelected()) {
			criteria = "BLOCKS";
		}
		else if (rbRebounds.isSelected()) {
			criteria = "REBOUNDS";
		}
		return criteria;
	}

	public String getComparison() {
		String comparison = "";
		if(rbEqual.isSelected()) {
			comparison = "EQUAL";
		} 
		else if (rbGreater.isSelected()) {
			comparison = "GREATER";
		} else if (rbLess.isSelected()) {
			comparison = "LESS";
		}
		return comparison;
	}



	public void showValidationErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error de validacion");
		alert.setHeaderText(null);
		alert.setContentText("Recuerde diligenciar cada uno de los campos");
		alert.showAndWait();
	}
}
