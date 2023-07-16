package algorithm;

import data.Column;
import javafx.scene.layout.Pane;

public class QuickSort extends Sort {

    private int size;
    private Pane drawPane;
    private Column[] cols;

    public QuickSort(Column[] cols, Pane drawPane) {
        super();
        this.size = cols.length;
        this.drawPane = drawPane;
        this.cols = cols;
        this.recWidth = drawPane.getWidth() / this.size;
    }

    private int partition(Column[] cols, int low, int high) {
        Column pivot = cols[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (cols[j].getValue() <= pivot.getValue()) {
                i++;
                swap(cols, i, j);
            }
        } 
        swap(cols, i + 1, high);
        return i + 1;
    }

    public void quickSort(Column[] cols, int low, int high) {
        if (low < high) {
            int pi = partition(cols, low, high);

            quickSort(cols, low, pi - 1);
            quickSort(cols, pi + 1, high);
        }
    }

    @Override
    public void sort() {
        quickSort(cols, 0, this.size - 1);
    }

}
