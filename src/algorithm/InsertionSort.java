package algorithm;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import data.Unit;

import java.util.ArrayList;

public class InsertionSort extends Sort {
    public InsertionSort(double width) {
        super(width);
    }

    private void insertionSort(Unit[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Unit key = arr[i];
            colorUnit(arr, CHECKING, i);
            ParallelTransition pt = new ParallelTransition();
            int j = i - 1;
            colorUnit(arr, COMPARE, j);
            colorUnit(arr, START, j);
            while (j >= 0 && arr[j].getValue() > key.getValue()) {
                pt.getChildren().add(arr[j].move(this.getWidth()));
                arr[j + 1] = arr[j];
                j = j - 1;
                if (j > 0) {
                    colorUnit(arr, COMPARE, j);
                    colorUnit(arr, START, j);
                }if(j==0){
                    colorUnit(arr, COMPARE,j);
                    colorUnit(arr, START, j);
                }
            }
            // if(j>=0){
            //     colorUnit(arr, COMPARE,j);
            //     colorUnit(arr, START, j);
            colorUnit(arr, START, j + 1);
            arr[j + 1] = key;
            pt.getChildren().add(key.move(this.getWidth() * (j + 1 - i)));
            this.transitions.add(pt);
            colorUnit(arr, START, j + 1);

        }
    }
    public ArrayList<Transition> sorting(Unit[] arr) {
        insertionSort(arr);
        colorUnit(arr, CHECKING);   
        return this.transitions;
    }
}
