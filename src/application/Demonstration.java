package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demonstration extends Application {
	@Override
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/screen/Demonstration.fxml"));
		
		Scene canvas = new Scene(root);
		stage.setScene(canvas);
		stage.setTitle("Sorting Visualization");
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}