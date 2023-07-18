package controller;

import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.QuickSort;
import algorithm.Sort;
import data.DataController;
import data.Unit;
import javax.swing.JOptionPane;

public class DemonstrationController {
    private final SequentialTransition sequentialTransition = new SequentialTransition();
    private Unit[] units;
    DataController dataController = new DataController();
    private InputTransformer transformer = new InputTransformer();
    private double width;
	public int speed;
	private int size;
	private int[] array = new int[size];	
	private int curInputArrayOption = 0; // 0 for manual, 1 for random	
	private static final int MAX_ARRAY_LENGTH = 300;
	
	private String[] inputArrayOption = {"Random", "Manual"};
	
//------------------------------------Setter and Getter------------------------
    public int getSpeed() {
        return speed;
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
//---------------------------------------FXML---------------------------------------------
    @FXML
    private TextField ArraySizeTF;

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
    void handleArraySizeHelpBtn(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Enter the size of the array you want to generate. \n" +
                "The size must be an integer number between 1 and 300.");
    }

    @FXML
    void handleGenerateBtn(ActionEvent event) {
        drawPane.getChildren().clear();
    	arrSizeLabel.setText(ArraySizeTF.getText());
    	curInputArrayOption = inputOptionComboBox.getSelectionModel().getSelectedIndex();
    	switch (curInputArrayOption) {
    		case 0: //random
    			size = Integer.parseInt(ArraySizeTF.getText());
                if (size > MAX_ARRAY_LENGTH) {
                    JOptionPane.showMessageDialog(null, "The size of the array must be less than 300.");
                    return;
                }
                this.units = dataController.randomArr(size);
                createUnits(this.units);
    			break;
    		case 1: //manual
    			int[] newArr;
    			try {
    				newArr = transformer.StrToArr(transformer.deleteNewLineTabSpaces(inputArrayTA.getText()), ",");
    				size = newArr.length;
    				array = newArr;
                    this.units = dataController.createArr(newArr);
                    createUnits(this.units);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			break;
    	}
    }

    @FXML
    void handleMenuBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/MenuScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.show();
    }
    
    @FXML
    void handleSortBtn(ActionEvent event) {
        String alg = ((Stage) sortBtn.getScene().getWindow()).getTitle();
        double cwidth = drawPane.getWidth()/this.units.length;
        Sort sort = new Sort(cwidth);
        if (alg.equals("Bubble Sort")) {
            sort = new BubbleSort(cwidth);
        } else if (alg.equals("Insertion Sort")){
            sort = new InsertionSort(cwidth);
        } else if (alg.equals("Quick Sort")) {
            sort = new QuickSort(cwidth);
        }
        sequentialTransition.getChildren().clear();
        ArrayList<Transition> transitions = sort.sorting(this.units);
        for (Transition transition : transitions) {
            sequentialTransition.getChildren().add(transition);
        }
        sequentialTransition.play();
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
    void handleSpeedSlider(ActionEvent event){
    	speed = (int) Math.round(speedSlider.getValue());
    }
    
    @FXML
    void initialize() {	
    	inputOptionComboBox.getItems().removeAll(inputOptionComboBox.getItems());
    	inputOptionComboBox.getItems().addAll(inputArrayOption);
    	inputOptionComboBox.getSelectionModel().select(inputArrayOption[curInputArrayOption]);
    	
    	speedSlider.setMin(1);
    	speedSlider.setMax(100);
    	speedSlider.setValue(50);
    	speedLabel.setText("50ms");
        sequentialTransition.rateProperty().bind(speedSlider.valueProperty());
    	ArraySizeTF.setText("12");
    	
    	speedSlider.valueProperty().addListener(
    			new ChangeListener<Number>() {
    			public void changed(ObservableValue<? extends Number>
    					observable, Number oldNumber, Number newNumber)
    			{
    				speedLabel.setText(Math.round(newNumber.doubleValue()) + "ms");
    			}
    			});
    }
    
  //-------------------------------VISUALIZATION----------------------------------
    public void createUnits(Unit... units) {
        this.width = drawPane.getWidth()/units.length;
        double unitHeight = drawPane.getHeight()/getMaxHeight(units);
        for (int i = 0; i < units.length; i++) {
            Unit unit = units[i];
            unit.setWidth(width);
            unit.setHeight(unit.getValue() * unitHeight);
            unit.setTranslateX(i * width);
            unit.setLayoutY(drawPane.getHeight() - unit.getHeight());
            //fill with color 2A3950
            unit.setFill(Color.web("#2A3950"));
            drawPane.getChildren().add(unit);
        }
    }
    public int getMaxHeight(Unit... units) {
        int maxHeight = -1;
        for (Unit unit : units) {
            maxHeight = Math.max(maxHeight, unit.getValue());
        }
        return maxHeight;
    }
}
