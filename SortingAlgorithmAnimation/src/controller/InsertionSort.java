package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InsertionSort extends Sort{

	@FXML
	void handleSortBtn(ActionEvent event) {
	    // Disable sortBtn during sorting process
	    sortBtn.setDisable(true);

	    Task<Void> sortingTask = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            insertionSort(array);

	            // Update visualization on the JavaFX Application Thread
	            Platform.runLater(() -> {
	                updateProcess(size, array, -1, -1);
	                // Enable sortBtn after sorting process completion
	                sortBtn.setDisable(false);
	                // Inform the user about the completion
	                showAlert("Sorting Complete", "The sorting process has been completed.");
	            });

	            return null;
	        }
	    };

	    // Start the sorting task on a separate background thread
	    Thread sortingThread = new Thread(sortingTask);
	    sortingThread.setDaemon(true);
	    sortingThread.start();
	}

	private void insertionSort(int[] array) {
	    int n = array.length;

	    for (int i = 1; i < n; i++) {
	        int key = array[i];
	        int j = i - 1;

	        while (j >= 0 && array[j] > key) {
	            array[j + 1] = array[j];
	            j = j - 1;

	            // Update visualization on the JavaFX Application Thread
	            final int current = j + 1;
	            final int check = j;
	            Platform.runLater(() -> updateProcess(size, array, current, check));

	            try {
	                Thread.sleep(speed); // Delay for visualization
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        array[j + 1] = key;
	    }
	}

}
