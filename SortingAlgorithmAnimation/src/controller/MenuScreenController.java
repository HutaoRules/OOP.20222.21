package controller;

import java.io.IOException;
// import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class MenuScreenController{
    @FXML
    private void handleBubbleSortButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/Demonstration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleInsertionSortButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/Demonstration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleQuickSortButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/Demonstration.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleHelpButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Sorting Visualization");
        alert.setContentText("This program demonstrates 3 sorting algorithms: Bubble Sort, Insertion Sort, and Quick Sort. "
                + "You can choose a sorting algorithm and visualize the step-by-step sorting process.\n\n"
                + "To use the program:\n"
                + "- Select a sorting algorithm from the menu\n"
                + "- You can create an array by randomly generate or manually input. Click the 'CREATE' button to create an array\n"
                + "- Click the 'START' button to begin the sorting\n"
                + "- Follow the steps displayed to see the sorting in action\n\n"
                + "Enjoy sorting!");

        alert.showAndWait();
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Quit");
        alert.setHeaderText("Quit Sorting Visualization");
        alert.setContentText("Are you sure you want to quit?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Thoát ứng dụng
                Platform.exit();
            }
        });
    }
}