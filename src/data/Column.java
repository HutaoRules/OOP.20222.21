package data;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Column extends Rectangle {
    
    private int value;

    public Column(int value)
    {
        super();
        this.value = value;
    }

    // get value
    public int getValue()
    {
        return this.value;
    }

    public boolean compare(Column col)
    {
       if (this.value > col.getValue())
       {
        return true;
       }

       return false;
    }

   
}
