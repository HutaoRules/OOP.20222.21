package controller;

import java.util.Random;

import data.InputTransformer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sort {
	
	protected InputTransformer transformer = new InputTransformer();
	public int speed;
	
	protected Random r = new Random();
	protected int current;
	protected int check;
	protected int size;
	protected int[] array = new int[size];
	protected boolean isSorted;
	
	protected int curInputArrayOption = 0; // 0 for manual, 1 for random
	protected int curAlg = 0; // 0 for bubble, 1 for heap, 2 for quick sort
	
	protected static final int MAX_ARRAY_LENGTH = 300;
	
	protected String[] inputArrayOption = {"Random", "Manual"};
	
	
//-------------------------------DATA INIT-------------------------------------------
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
    
//------------------------------------Setter and Getter------------------------
    public boolean isIsSorted() {
		for (int i=0; i<array.length - 1; i++) {
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

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
    
//---------------------------------------FXML---------------------------------------------
    @FXML
    protected Label ArraySizeLabel;

    @FXML
    protected TextField ArraySizeTF;

    @FXML
    protected Label algorithmLabel;

    @FXML
    protected Label arrSizeLabel;

    @FXML
    protected Button arraySizeHelpBtn;

    @FXML
    protected Pane drawPane;

    @FXML
    protected Button generateBtn;

    @FXML
    protected TextArea inputArrayTA;

    @FXML
    protected ComboBox<String> inputOptionComboBox;

    @FXML
    protected Button menuBtn;

    @FXML
    protected Button sortBtn;

    @FXML
    protected Label speedLabel;

    @FXML
    protected Slider speedSlider;

    @FXML
    void handleArraySizeHelpBtn(ActionEvent event) {
    	
    }

    @FXML
    void handleGenerateBtn(ActionEvent event) {
    	
    	curInputArrayOption = inputOptionComboBox.getSelectionModel().getSelectedIndex();
    	switch (curInputArrayOption) {
    		case 0: //random
    			size = Integer.parseInt(ArraySizeTF.getText());
    			
    			array = new int[size];
    			for (int i = 0; i < size; i++) {
    				array[i] = i + 1;
    			}
    			for (int a = 0; a < 500; a++) {
    				for (int i = 0; i < size; i++) {
    					int rand = r.nextInt(size);
    					int temp = array[rand];
    					array[rand] = array[i];
    					array[i] = temp;
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
    	
    	arrSizeLabel.setText(size+"");
    }

    @FXML
    void handleMenuBtn(ActionEvent event) {
    	
    }
    
    @FXML
    void handleSortBtn(ActionEvent event) {
    	
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
    	inputOptionComboBox.getItems().removeAll(inputOptionComboBox.getItems());
    	inputOptionComboBox.getItems().addAll(inputArrayOption);
    	inputOptionComboBox.getSelectionModel().select(inputArrayOption[curInputArrayOption]);
    	speed = 50;
    	
    	speedSlider.setMin(0);
    	speedSlider.setMax(100);
    	speedSlider.setValue(50);
    	speedLabel.setText("50ms");
    	ArraySizeTF.setText("12");
    	
    	speedSlider.valueProperty().addListener(
    			new ChangeListener<Number>() {
    			public void changed(ObservableValue<? extends Number>
    					observable, Number oldNumber, Number newNumber)
    			{
    				speed = (int) speedSlider.getValue();
    				speedLabel.setText(Math.round(newNumber.doubleValue()) + "ms");
    			}
    			});
    }
    
  //-------------------------------VISUALIZATION----------------------------------
    public void drawCurrentState() {
    	drawPane.getChildren().clear();
        int maxHeight = -1;
        for (int i = 0; i < size; i++) {
            maxHeight = Math.max(maxHeight, array[i]);
        }
        
        double recWidth = drawPane.getWidth()/size;
        
        for (int i = 0; i < size; i++) {
            double HEIGHT = array[i] * (drawPane.getHeight()/maxHeight); // set height of element in graph.
            Rectangle rec = new Rectangle();
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
    
    protected void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}