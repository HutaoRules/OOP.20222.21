package algorithm;

import javafx.animation.Transition;
import data.Unit;

import java.util.ArrayList;

public class QuickSort extends Sort {
//    private int length;
//    public QuickSort(int[] arr)
//    {
//        super(arr);
//        this.length = arr.length;
//    }
//    private int partition(int[] arr, int low, int high)
//    {
//        int pivot = arr[high];
//        int i = low-1;
//        for (int j = low; j < high; j++)
//        {
//            if (arr[j] < pivot)
//            {
//                i++;
//                swap(arr, i, j);
//            }
//        }
//        swap(arr, i+1, high);
//        return i+1;
//    }
//    private void quickSort(int[] arr, int low, int high)
//    {
//        if (low < high)
//        {
//            int pi = partition(arr, low, high);
//            quickSort(arr, low, pi-1);
//            quickSort(arr, pi+1, high);
//        }
//    }
//    @Override
//    public void sort(int[] arr)
//    {
//        quickSort(arr, 0, this.length-1);
//    }
    public QuickSort(double width) {
        super(width);
    }
    private int partition(Unit[] arr, int low, int high) {
        Unit pivot = arr[high];
        colorUnit(arr, CHECKING, high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            colorUnit(arr, COMPARE, j);
            if (arr[j].getValue() < pivot.getValue()) {
                i++;
                swap(arr, i, j);
                colorUnit(arr, START, i);
            } else {
                colorUnit(arr, START, j);
            }
        }
            swap(arr, i + 1, high);
            colorUnit(arr, START, i + 1);
            return i + 1;
    }
    private void quickSort(Unit[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    public ArrayList<Transition> sorting(Unit[] arr) {
        quickSort(arr, 0, arr.length-1);
        colorUnit(arr, CHECKING);
        return this.transitions;
    }
}
