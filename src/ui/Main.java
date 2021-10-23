package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Fiba;

public class Main extends Application{

	private Fiba fiba;
	private FibaGUI fibaGUI;
	
	public Main() {
		fiba = new Fiba();
		fibaGUI = new FibaGUI();
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
