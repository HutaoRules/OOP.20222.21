package algorithm;

import javafx.animation.Transition;
import data.Unit;

import java.util.ArrayList;

public class BubbleSort extends Sort {
    public BubbleSort(double width) {
        super(width);
    }
    private void compare(Unit[] arr, int i, int j) {
        colorUnit(arr, COMPARE, i, j);
        if (arr[i].getValue() > arr[j].getValue()) {
            swap(arr, i, j);
        }
        colorUnit(arr, START, i, j);
    }
    private void bubbleSort(Unit[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j <arr.length-i-1; j++) {
                compare(arr, j, j+1);
            }
            colorUnit(arr, CHECKING, arr.length - i - 1);
        }
    }
    public ArrayList<Transition> sorting(Unit[] arr) {
        bubbleSort(arr);
        colorUnit(arr, CHECKING);
        return this.transitions;
    }
}
