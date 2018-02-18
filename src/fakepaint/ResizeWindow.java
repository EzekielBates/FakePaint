package fakepaint;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ResizeWindow extends GridPane{
	
	private Text tWidth = new Text("Width");
	private Text tHeight = new Text("Height");
	
	private TextField widthInput = new TextField("500");
	private TextField heightInput = new TextField("500");
	
	private Button btEnter = new Button("Enter");

	protected ResizeWindow(){
		add(tWidth,0,0);
		add(tHeight,1,0);
		add(widthInput,0,1);
		add(heightInput,1,1);
		add(btEnter,1,2);
	}

	public TextField getWidthInput() {
		return widthInput;
	}

	public TextField getHeightInput() {
		return heightInput;
	}
	
	public Button getBtEnter() {
		return btEnter;
	}
}
