package ui;

import java.io.IOException;

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
    public void btSearchPlayers(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/searchPlayer.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo1.jpg)");
    	
    }

	public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/WelcomePage.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		//mainPane.setStyle("-fx-background-image: url(/ui/fondo1.jpg)");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

	  
	    private void initializeTableViewSearchedPlayers() {
	    	ObservableList<Player> observableList;
			observableList = FXCollections.observableArrayList(fiba.getPlayers()); ///to change: get the players searched
			tvOfPlayers.setItems(observableList);
			colName.setCellValueFactory(new PropertyValueFactory<Player, String>("Name"));
			colAge.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Age"));
			colTeam.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Team"));
			colPoints.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Points"));
			colRebounds.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Bounces"));
			colAssists.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Assists"));
			colSteals.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Steals"));
			colBlocks.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Blocks"));
		}

	    
	    @FXML
	    public void searchPlayers(ActionEvent event) {
	    	if(searchCriteria.getSelectedToggle()!=null && comparison.getSelectedToggle()!=null && !txtValue.getText().isEmpty()) {
	    		//initializeTableViewSearchedPlayers();
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
