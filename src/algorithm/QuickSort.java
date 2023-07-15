package src.algorithm;

public class QuickSort extends Sort {
    private int length;
    public QuickSort(int[] arr)
    {
        super(arr);
        this.length = arr.length;
    }
    private int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = low-1;
        for (int j = low; j < high; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }
    private void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    @Override
    public void sort(int[] arr)
    {
        quickSort(arr, 0, this.length-1);
    }

}