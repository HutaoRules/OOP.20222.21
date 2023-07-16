package algorithm;

import data.Column;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public abstract class Sort implements Sortable {
   
    public Transition[] trans = new Transition[10000];
    public int count = 0;
    public double recWidth;

    public Sort()
    {
      
    }
    
    public abstract void sort();
    
    @Override
    public void swap(Column[] cols, int i1, int i2)
    {   
        ParallelTransition paTran = new ParallelTransition();
       
        TranslateTransition tran1 = new TranslateTransition(Duration.millis(200), cols[i1]);
        tran1.setByX((i2-i1)*recWidth);
        
        TranslateTransition tran2 = new TranslateTransition(Duration.millis(200), cols[i2]);
        tran2.setByX((i1-i2)*recWidth);
        
        
        Column temp = cols[i1];
        cols[i1] = cols[i2];
        cols[i2] = temp;



        paTran.getChildren().addAll(tran1, tran2);

        trans[count] = paTran;
        count += 1;
    }
    
    
    
}
