package application;

import controller.MenuScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root  = FXMLLoader.load(getClass().getResource("/screen/MenuScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/MenuScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setOnCloseRequest(event -> {
            event.consume(); // Hủy bỏ sự kiện đóng cửa sổ mặc định

            // Gọi phương thức handleExitButton từ MenuScreenController
            MenuScreenController controller = loader.getController();
            controller.handleExitButton(null);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
