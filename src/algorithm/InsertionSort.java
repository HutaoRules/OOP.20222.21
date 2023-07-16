package algorithm;

import data.Column;
import javafx.animation.ParallelTransition; 
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class InsertionSort extends Sort {

    private int size;
  private Pane drawPane;
  private Column[] cols;

    public InsertionSort(Column[] cols, Pane drawPane) {
        super();
        this.size = cols.length;
        this.drawPane = drawPane;
        this.cols = cols;
        this.recWidth = drawPane.getWidth()/this.size;
    }

    public void insertionSort() {

        
        Column key;


        for (int i = 1; i < cols.length; i++) {

			 key = cols[i]; 
			int j = i-1;
            
			while(j >= 0 && cols[j].compare(key)) {
				
                swap(cols, j, j+1);
                
				j -= 1;
			}
		
		}

    }
    @Override
    public void sort() {
        insertionSort();
    }
    
    public TranslateTransition move(Column[] cols, int i1,int i2)
    {
        TranslateTransition tran = new TranslateTransition(Duration.millis(200), cols[i1]);
        tran.setByX((i2-i1)*recWidth);
        
        return tran;
    }

    
}
