package algorithm;

import data.Column;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class Sort implements Sortable {
   
    public Transition[] trans = new Transition[10000];
    public int count = 0;
    public double recWidth;
    private double speed;

    public Sort()
    {
      
    }
    
    public abstract void sort();
    
    @Override
    public void swap(Column[] cols, int i1, int i2)
    {   
        ParallelTransition paTran = new ParallelTransition();
        

        TranslateTransition tran1 = new TranslateTransition(Duration.millis(speed), cols[i1]);
        tran1.setByX((i2-i1)*recWidth);
        
        TranslateTransition tran2 = new TranslateTransition(Duration.millis(speed), cols[i2]);
        tran2.setByX((i1-i2)*recWidth);

        Column temp = cols[i1];
        cols[i1] = cols[i2];
        cols[i2] = temp;

        paTran.getChildren().addAll(tran1, tran2);

        trans[count] = paTran;
        count += 1;
    }
    
    void colorColumn(Column[] arr, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();
        if(a.length == 0) {
            for (int i = 0; i < arr.length; i++) {
                FillTransition ft = new FillTransition();
                ft.setShape(arr[i]);
                ft.setToValue(color);
                ft.setDuration(Duration.millis(1));
                pt.getChildren().add(ft); 
            }
            trans[count] = pt;
            count += 1;
            return;
        }

        for (int i = 0; i < a.length; i++) {
            FillTransition ft = new FillTransition();
            ft.setShape(arr[a[i]]);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft); 
        }
        trans[count] = pt;
        count += 1;
    }
    
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
}
