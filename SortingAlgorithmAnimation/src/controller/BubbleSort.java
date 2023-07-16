package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BubbleSort extends Sort{
	@FXML
	void handleSortBtn(ActionEvent event) {
		sortBtn.setDisable(true);
		
		Task<Void> sortingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < size - 1; i++) {
                    for (int j = 0; j < size - i - 1; j++) {
                        if (array[j] > array[j + 1]) {
                            // Swap elements
                            swap(j, j + 1);
                            setCheck(j+2);
                            setCurrent(j+1);
                            // Update visualization on the JavaFX Application Thread
                            Platform.runLater(() -> {
                            	updateProcess(size, array, current, check);
                                
                            	});

                            try {
                                Thread.sleep(speed); // Delay for visualization
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                sortBtn.setDisable(false);
                // Inform the user about the completion
                Platform.runLater(() -> showAlert("Sorting Complete", "The sorting process has been completed."));
            
                return null;
            }
        };

        // Start the sorting task on a separate background thread
        Thread sortingThread = new Thread(sortingTask);
        sortingThread.setDaemon(true);
        sortingThread.start();
	}
}
