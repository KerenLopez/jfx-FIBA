package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import exceptions.NegativeValueException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Fiba;
import model.Player;
import thread.ImportDataThread;

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
	private CheckBox cbxEqual;

	@FXML
	private CheckBox cbxGreater;

	@FXML
	private CheckBox cbxLess;

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

	@FXML
	private Button btAddPlayer;


	public FibaGUI(Fiba f) {
		fiba = f;
	}

	public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WelcomePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/logo.jpeg)");
	}

	private void initializeTableViewOfAddedPlayers() {
		ObservableList<Player> observableList;
		observableList = FXCollections.observableArrayList(fiba.returnPlayers());
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
	public void clickOnTableViewOfAddedPlayers(MouseEvent event) {
		Player selectedPlayer= tvOfPlayers.getSelectionModel().getSelectedItem();
		if (selectedPlayer!=null) {
			txtName.setText(selectedPlayer.getName());;
			txtAge.setText(""+selectedPlayer.getAge());;
			txtTeam.setText(selectedPlayer.getTeam());;
			txtPoints.setText(""+selectedPlayer.getPoints());
			txtBounces.setText(""+selectedPlayer.getBounces());
			txtAssists.setText(""+selectedPlayer.getAssists());
			txtSteals.setText(""+selectedPlayer.getSteals());
			txtBlocks.setText(""+selectedPlayer.getBlocks());
			btAddPlayer.setDisable(true);
		}
	}

	@FXML
	public void addPlayer(ActionEvent event) {
		
		if(!txtName.getText().equals("") && !txtAge.getText().equals("") && !txtTeam.getText().equals("") && !txtPoints.getText().equals("") && !txtBounces.getText().equals("") && !txtAssists.getText().equals("") && !txtSteals.getText().equals("") && !txtBlocks.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				fiba.addPlayer(txtName.getText(), txtAge.getText(), txtTeam.getText(), txtPoints.getText(), txtBounces.getText(), txtAssists.getText(), txtSteals.getText(), txtBlocks.getText());
				alert1.setContentText("El jugador ha sido agregado exitosamente");
				alert1.showAndWait();
			}catch(NumberFormatException num) {
				alert1.setContentText("Debe ingresar un numero dentro de los campos presentados que asi lo requieran");
				alert1.showAndWait();
			}catch(NegativeValueException value) {
				alert1.setContentText(value.getMessage());
				alert1.showAndWait();
			}
			
			initializeTableViewOfAddedPlayers();
			txtName.clear();
			txtAge.clear();
			txtTeam.clear();
			txtPoints.clear();
			txtBounces.clear();
			txtAssists.clear();
			txtSteals.clear();
			txtBlocks.clear();
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void deletePlayer(ActionEvent event) {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Confirmacion de proceso");
		alert1.setHeaderText(null);
		alert1.setContentText("¿Esta seguro de que quiere eliminar este jugador?");
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == ButtonType.OK){
			fiba.deletePlayer(tvOfPlayers.getSelectionModel().getSelectedItem());
			Alert alert2 = new Alert(AlertType.INFORMATION);
			alert2.setTitle("Informacion");
			alert2.setHeaderText(null);
			alert2.setContentText("El jugador elegido ha sido eliminado exitosamente");
			alert2.showAndWait();
			txtName.clear();
			txtAge.clear();
			txtTeam.clear();
			txtPoints.clear();
			txtBounces.clear();
			txtAssists.clear();
			txtSteals.clear();
			txtBlocks.clear();
			tvOfPlayers.getItems().clear();
			initializeTableViewOfAddedPlayers();
			btAddPlayer.setDisable(false);
		}
	}

	@FXML
	public void returnToMenu(ActionEvent event) throws IOException {
		showWelcomeWindow();
	}

	@FXML
	public void updatePlayer(ActionEvent event) throws NegativeValueException {
		boolean updated = false;
		if(!txtName.getText().equals("") && !txtAge.getText().equals("") && !txtTeam.getText().equals("") && !txtPoints.getText().equals("") && !txtBounces.getText().equals("") && !txtAssists.getText().equals("") && !txtSteals.getText().equals("") && !txtBlocks.getText().equals("")) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("Error de validacion");
			alert1.setHeaderText(null);
			try {
				updated = fiba.updatePlayer(tvOfPlayers.getSelectionModel().getSelectedItem(), txtName.getText(), txtAge.getText(), txtTeam.getText(), txtPoints.getText(), txtBounces.getText(), txtAssists.getText(), txtSteals.getText(), txtBlocks.getText());
			}catch(NumberFormatException num) {
				alert1.setContentText("Debe ingresar un numero dentro de los campos presentados que asi lo requieran");
				alert1.showAndWait();
			}catch(NegativeValueException value) {
				alert1.setContentText(value.getMessage());
				alert1.showAndWait();
			}
			if(updated==false) {
				alert1.setContentText("Ya existe un jugador agregado con dicho nombre, intentelo nuevamente");
				alert1.showAndWait();
			}else {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Informacion");
				alert2.setHeaderText(null);
				alert2.setContentText("El jugador elegido ha sido actualizado exitosamente");
				alert2.showAndWait();
			}
			txtName.clear();
			txtAge.clear();
			txtTeam.clear();
			txtPoints.clear();
			txtBounces.clear();
			txtAssists.clear();
			txtSteals.clear();
			txtBlocks.clear();
			tvOfPlayers.getItems().clear();
			initializeTableViewOfAddedPlayers();
			btAddPlayer.setDisable(false);
		}else {
			showValidationErrorAlert();
		}
	}

	@FXML
	public void btImportPlayers(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Abrir el archivo");
		File f=fileChooser.showOpenDialog(mainPane.getScene().getWindow());
		if(f!=null) {
			alert.setTitle("Importar jugadores");
			try {
				ImportDataThread importDataThread = new ImportDataThread(fiba,  new BufferedReader(new FileReader(f.getAbsolutePath())));

				importDataThread.start();

				importDataThread.join();


				alert.setContentText("Los jugadores fueron importados exitosamente");
				alert.showAndWait();
			}catch(IOException | InterruptedException e){
				alert.setContentText("Los jugadores no se importaron.");
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

	@FXML
	public void btSearchPlayers(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/searchPlayer.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/fondo.jpg)");
	}


	private void initializeTableViewSearchedPlayers(ObservableList<Player> observableList) {
		tvSearchedPlayers.getItems().clear();
		tvSearchedPlayers.setItems(observableList);
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
		if(searchCriteria.getSelectedToggle()!=null && !(cbxGreater.isSelected() && cbxLess.isSelected()) && !txtValue.getText().isEmpty()) {
    		Alert alert1 = new Alert(AlertType.INFORMATION);
		    alert1.setTitle("Jugador(es) encontrado(s)");
		    alert1.setHeaderText(null);
			if(getSearchCriteria().equals("POINTS")) {
				long startABB= System.nanoTime();
				ObservableList<Player> playersList = FXCollections.observableArrayList(fiba.searchPlayersABB(txtValue.getText(), getSearchCriteria()+getComparison()));
				long endABB = System.nanoTime();
		    	long timeABB = endABB-startABB;
		    	initializeTableViewSearchedPlayers(playersList);
		    	long startAVL= System.nanoTime();
				fiba.searchPlayersAVL(txtValue.getText(), getComparison());
				long endAVL = System.nanoTime();
		    	long timeAVL = endAVL-startAVL;
		    	alert1.setContentText("Tiempo que tardó la búsqueda en ABB: "+timeABB+" nanosegundos\n Tiempo que tardó la búsqueda en AVL: "+timeAVL+" nanosegundos");
			    alert1.showAndWait();
			}else if(getSearchCriteria().equals("ASSISTS")) {
				long startABB= System.nanoTime();
				ObservableList<Player> playersList = FXCollections.observableArrayList(fiba.searchPlayersABB(txtValue.getText(), getSearchCriteria()+getComparison()));
				long endABB = System.nanoTime();
		    	long timeABB = endABB-startABB;
		    	initializeTableViewSearchedPlayers(playersList);
		    	long startAVL= System.nanoTime();
				fiba.searchPlayersAVL(txtValue.getText(), getComparison());
				long endAVL = System.nanoTime();
		    	long timeAVL = endAVL-startAVL;
		    	alert1.setContentText("Tiempo que tardó la búsqueda en ABB: "+timeABB+" nanosegundos\n"+"Tiempo que tardó la búsqueda en AVL: "+timeAVL+" nanosegundos");
			    alert1.showAndWait();
			}else if(getSearchCriteria().equals("STEALS")) {
				fiba.searchPlayersRedBlackTree(txtValue.getText(), getSearchCriteria()+getComparison());
			}else if(getSearchCriteria().equals("BLOCKS")){
				fiba.searchPlayersAVL(txtValue.getText(), getSearchCriteria()+getComparison());
			}else {
				ObservableList<Player> playersList = FXCollections.observableArrayList(fiba.searchPlayersLinearly(txtValue.getText(), getComparison()));
				initializeTableViewSearchedPlayers(playersList);
			}
		}else if(cbxGreater.isSelected() && cbxLess.isSelected()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error de validacion");
			alert.setHeaderText(null);
			alert.setContentText("Solo se aceptan las sgts opciones: menores e iguales, mayores e iguales, mayores, menores. No mayores y menores a la vez");
			alert.showAndWait();
		}else {
			showValidationErrorAlert();
		}
		txtValue.clear();
		searchCriteria.getSelectedToggle().setSelected(false);
		cbxGreater.setSelected(false);
		cbxLess.setSelected(false);
		cbxEqual.setSelected(false);
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
		if(cbxEqual.isSelected()) {
			comparison += "EQUAL";
		} 
		if (cbxGreater.isSelected()) {
			comparison += "GREATER";
		}
		if (cbxLess.isSelected()) {
			comparison += "LESS";
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
