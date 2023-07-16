package src.data;

public class DataController {
    private int[] arr;
    private int len;
    public DataController(int[] arr) {
        this.arr = arr;
        this.len = arr.length;
    }
    public int[] getArr() {
        return arr;
    }
    public int getLen() {
        return len;
    }
    public void randomArr() {
        int[] narr = new int[len];
        for (int i = 0; i < len; i++) {
            narr[i] = (int) (Math.random() * 100);
        }
        this.arr = narr;
    }
}


