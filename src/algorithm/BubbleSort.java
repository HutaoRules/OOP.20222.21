package algorithm;

import controller.DemonstrationController;
import data.Column;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BubbleSort extends Sort {

  private int size;
  private Pane drawPane;
  private Column[] cols;


  public BubbleSort( Column[] cols, Pane drawPane) {
    super();
    this.size = cols.length;
    this.drawPane = drawPane;
    this.cols = cols;
    this.recWidth = drawPane.getWidth()/this.size;
  }

  

  

  public void bubbleSort() {
    int i, j;
    boolean swapped = false;
    double temp;  

    for (i = 0; i < this.size - 1; i++) {

      swapped = false;

      for (j = 0; j < this.size - i - 1; j++) {
        if (cols[j].compare(cols[j + 1])) {

          swap(cols, j, j+1);

          swapped = true;

        }
      }
      if (swapped == false) {
        break;

      }
    }

  }

  @Override
  public void sort() {
    bubbleSort();
  }

}
