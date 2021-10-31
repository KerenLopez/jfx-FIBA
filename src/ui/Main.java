package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Fiba;

public class Main extends Application{

	private Fiba fiba;
	private FibaGUI fibaGUI;
	
	public Main() {
		Fiba fibaData = new Fiba();
		
		try {
			fiba = fiba.loadDataFIBA(fibaData);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("FIBA");
			alert.setHeaderText(null);
			if(!fiba.getPlayersByBounces().isEmpty()) {
				alert.setContentText("Se cargaron datos de archivo");
				alert.showAndWait();
			}
			
			
		
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FIBA");
			alert.setHeaderText(null);
			alert.setContentText("Error al cargar datos de archivo");
			alert.showAndWait();
		} 
		
		
		fibaGUI = new FibaGUI(fiba);
		
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
		
		fxmlLoader.setController(fibaGUI);
		Parent root= fxmlLoader.load();
		
		Scene scene= new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Sistema FIBA");
		primaryStage.show();
		fibaGUI.showWelcomeWindow();
	}

}
