package controller;

import java.util.Random;

import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.QuickSort;
import algorithm.Sort;
import data.Column;

public class DemonstrationController {

    private InputTransformer transformer = new InputTransformer();
    public int speed;

    private Random r = new Random();
    private int current;
    private int check;
    private int size;
    private int[] array = new int[size];
    private boolean isSorted;
    private boolean isPause;
    private boolean isStop;

    private int compared = 0;
    private int arrayAccessed = 0;
    private int curInputArrayOption = 0; // 0 for manual, 1 for random
    private int curAlg = 0; // 0 for bubble, 1 for heap, 2 for quick sort
    private String[] sortingProcessListMsg = { "BUBBLE SORT", "HEAP SORT", "QUICK SORT" };
    private String sortingProcessMsg = sortingProcessListMsg[curAlg];
    private String[] algorithmOption = { "Bubble Sort", "Insertion Sort", "Quick Sort" };

    private static final int MAX_ARRAY_LENGTH = 300;

    private Column[] cols = new Column[size];

    private String[] inputArrayOption = { "Random", "Manual" };

    // -------------------------------DATA
    // INIT-------------------------------------------
    public void basicArray() {
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
    }

    public void swap(int p1, int p2) {
        int tempt = array[p1];
        array[p1] = array[p2];
        array[p2] = tempt;
    }

    public void genRandomArr() {
        basicArray();
        for (int x = 0; x < 50; x++) {
            for (int i = 0; i < size; i++) {
                int rand = r.nextInt(size);
                swap(rand, i);
            }
        }
    }

    // ------------------------------------Setter and Getter------------------------
    public boolean isIsSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) {
                isSorted = false;
                return false;
            }
        }
        return isSorted = true;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCurAlg() {
        return curAlg;
    }

    public int getCompared() {
        return compared;
    }

    public void setCompared(int _compared) {
        this.compared = _compared;
    }

    public int getArrayAccessed() {
        return arrayAccessed;
    }

    public void setArrayAccessed(int _accessed) {
        this.arrayAccessed = _accessed;
    }

    public String getSortingProcessMsg() {
        return sortingProcessMsg;
    }

    public void setSortingProcessMsg(String _sortingProcessMsg) {
        this.sortingProcessMsg = _sortingProcessMsg;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int length) {
        this.size = length;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public boolean isIsPause() {
        return isPause;
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }

    public boolean isIsStop() {
        return isStop;
    }

    public void setIsStop(boolean isStop) {
        this.isStop = isStop;
    }

    // ---------------------------------------FXML---------------------------------------------
    @FXML
    private Label ArraySizeLabel;

    @FXML
    private TextField ArraySizeTF;

    @FXML
    private Label algorithmLabel;

    @FXML
    private Label arrSizeLabel;

    @FXML
    private Button arraySizeHelpBtn;

    @FXML
    private Pane drawPane;

    @FXML
    private Button generateBtn;

    @FXML
    private TextArea inputArrayTA;

    @FXML
    private ComboBox<String> inputOptionComboBox;

    @FXML
    private Button menuBtn;

    @FXML
    private Button sortBtn;

    @FXML
    private Label speedLabel;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label timeLabel;

    @FXML
    private ComboBox algorithmComboBox;

    @FXML
    void handleArraySizeHelpBtn(ActionEvent event) {

    }

    @FXML
    void handleGenerateBtn(ActionEvent event) {
        arrSizeLabel.setText(ArraySizeTF.getText());
        curInputArrayOption = inputOptionComboBox.getSelectionModel().getSelectedIndex();
        switch (curInputArrayOption) {
            case 0: // random
                size = Integer.parseInt(ArraySizeTF.getText());

                array = new int[size];
                cols = new Column[size];
                for (int i = 0; i < size; i++) {
                    array[i] = i + 1;
                    cols[i] = new Column(i + 1);
                }
                for (int a = 0; a < 500; a++) {
                    for (int i = 0; i < size; i++) {
                        int rand = r.nextInt(size);
                        int temp = array[rand];
                        array[rand] = array[i];
                        array[i] = temp;

                        int ctemp = cols[rand].getValue();
                        cols[rand] = cols[i];
                        cols[i] = new Column(ctemp);
                    }
                }

                drawCurrentState();
                break;
            case 1:
                int[] newArr;
                try {
                    newArr = transformer.StrToArr(transformer.deleteNewLineTabSpaces(inputArrayTA.getText()), ",");
                    size = newArr.length;
                    array = newArr;
                    drawCurrentState();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @FXML
    void handleMenuBtn(ActionEvent event) {

    }

    @FXML
    void getAlgorithm(ActionEvent e) {

    }

    @FXML
    void handleSortBtn(ActionEvent event) {

        int chosenAlgorithm = inputOptionComboBox.getSelectionModel().getSelectedIndex();

        if (chosenAlgorithm == 2) {
            QuickSort qSort = new QuickSort(cols, drawPane);
            qSort.quickSort(cols, 0, cols.length - 1);
            SequentialTransition st = new SequentialTransition();

            for (int i = 0; i < qSort.count; i++) {
                st.getChildren().add(qSort.trans[i]);
            }
            st.play();
        }

        else if (chosenAlgorithm == 1)
        {
            InsertionSort iSort = new InsertionSort(cols, drawPane);
            iSort.insertionSort();
            SequentialTransition st = new SequentialTransition();

            for (int i = 0; i < iSort.count; i++) {
                st.getChildren().add(iSort.trans[i]);
            }
            st.play();
        }
        else
        {
            BubbleSort bSort = new BubbleSort(cols, drawPane);
            bSort.bubbleSort();
            SequentialTransition st = new SequentialTransition();

            for (int i = 0; i < bSort.count; i++) {
                st.getChildren().add(bSort.trans[i]);
            }
            st.play();
        }

    }

    @FXML
    void handleInputOptionComboBox(ActionEvent event) {
        curInputArrayOption = inputOptionComboBox.getSelectionModel().getSelectedIndex();
        switch (curInputArrayOption) {
            case 0:
                inputArrayTA.setDisable(true);
            case 1:
                inputArrayTA.setDisable(false);
        }
    }

    @FXML
    void initialize() {
        // inputOptionComboBox.getItems().removeAll(inputOptionComboBox.getItems());
        inputOptionComboBox.getItems().addAll(inputArrayOption);
        inputOptionComboBox.getSelectionModel().select(inputArrayOption[curInputArrayOption]);

        algorithmComboBox.getItems().addAll(algorithmOption);

        speedSlider.setMin(0);
        speedSlider.setMax(100);
        speedSlider.setValue(50);
        speedLabel.setText("50ms");
        ArraySizeTF.setText("12");

        speedSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number> observable, Number oldNumber,
                            Number newNumber) {
                        speedLabel.setText(Math.round(newNumber.doubleValue()) + "ms");
                    }
                });
        speed = (int) Math.round(speedSlider.getValue());
    }

    // -------------------------------VISUALIZATION----------------------------------
    public void drawCurrentState() {
        drawPane.getChildren().clear();
        int maxHeight = -1;
        for (int i = 0; i < size; i++) {
            maxHeight = Math.max(maxHeight, array[i]);
        }
        Rectangle rec;
        double recWidth = drawPane.getWidth() / size;

        for (int i = 0; i < size; i++) {
            double HEIGHT = array[i] * (drawPane.getHeight() / maxHeight); // set height of element in graph.
            rec = cols[i];
            rec.setHeight(HEIGHT);
            rec.setWidth(recWidth);
            rec.setStroke(Color.BLACK);
            rec.setStrokeWidth(1);
            rec.setStroke(Color.WHITE);
            rec.setVisible(true);

            if (current > -1 && i == current) {
                rec.setFill(Color.RED); // color of current traversing element
            }
            if (check > -1 && i == check) {
                rec.setFill(Color.GREEN); // color of current checking element
            }
            double curRectPosX = i * recWidth;
            // fill rectangle element in graph

            rec.setX(curRectPosX);
            rec.setY(drawPane.getHeight() - HEIGHT);
            drawPane.getChildren().add(rec);
        }
    }

    public void updateProcess(int length, int[] array, int current, int check) {
        this.size = length;
        this.array = array;
        this.current = current;
        this.check = check;

        drawCurrentState();
    }

    public void delay() {
        try {
            Thread.sleep(speed);
        } catch (Exception e) {
        }
    }

}
