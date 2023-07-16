package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HeapSort extends Sort{
    @FXML
    void handleSortBtn(ActionEvent event) {
    	if (isIsSorted()) {
            showAlert("Sorting Complete", "The sorting process has been completed.");
    	} else {
        Task<Void> sortingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                heapSort(array);

                // Update visualization on the JavaFX Application Thread
                Platform.runLater(() -> {
                	drawCurrentState();
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
    }

    private void heapSort(int[] array) {
        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Perform heap sort
        for (int i = n - 1; i >= 0; i--) {
            // Swap root (maximum element) with the last element
            swap(0, i);

            // Update visualization on the JavaFX Application Thread
            Platform.runLater(() -> drawCurrentState());

            // Heapify the reduced heap
            heapify(array, i, 0);

            try {
                Thread.sleep(speed); // Delay for visualization
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void heapify(int[] array, int n, int root) {
        int largest = root;
        int leftChild = 2 * root + 1;
        int rightChild = 2 * root + 2;

        // Find the largest element among the root and its children
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // If the largest element is not the root, swap them and heapify the affected subtree
        if (largest != root) {
            swap(root, largest);
            setCurrent(root);
            setCheck(largest);
            // Update visualization on the JavaFX Application Thread
            Platform.runLater(() -> updateProcess(size, array, current, check));

            heapify(array, n, largest);
        }
    }
    
    @FXML
    void handleInputOptionComboBox(ActionEvent event) {
    	curInputArrayOption = inputOptionComboBox.getSelectionModel().getSelectedIndex();
    	switch (curInputArrayOption) {
    		case 0:
    			inputArrayTA.setDisable(true);
    		case 1:
                inputArrayTA.setDisable(false);
    	}
    }
}
