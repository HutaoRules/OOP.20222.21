package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class QuickSort extends Sort{
	@FXML
	void handleSortBtn(ActionEvent event) {
		sortBtn.setDisable(true);
	    Task<Void> sortingTask = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            quickSort(array, 0, array.length - 1);

	            // Update visualization on the JavaFX Application Thread
	            Platform.runLater(() -> {
	            	updateProcess(size, array, -1, -1);
	            	sortBtn.setDisable(false);
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

	private void quickSort(int[] array, int low, int high) {
	    if (low < high) {
	        int pivotIndex = partition(array, low, high);

	        // Recursively sort the two partitions
	        quickSort(array, low, pivotIndex - 1);
	        quickSort(array, pivotIndex + 1, high);

	        // Update visualization on the JavaFX Application Thread
	        Platform.runLater(() -> updateProcess(size, array, -1, -1));
	    }
	}

	private int partition(int[] array, int low, int high) {
	    int pivot = array[high];
	    int i = low - 1;

	    for (int j = low; j < high; j++) {
	        final int currentIndex = j; // Capture the value of j in a final variable
	        final int checkIndex = i;

	        if (array[currentIndex] < pivot) {
	            i++;
	            swap(i, currentIndex);

	            // Update visualization on the JavaFX Application Thread
	            Platform.runLater(() -> updateProcess(size, array, currentIndex, checkIndex));

	            try {
	                Thread.sleep(speed); // Delay for visualization
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    swap(i + 1, high);
	    return i + 1;
	}


}
