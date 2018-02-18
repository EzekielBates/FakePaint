package fakepaint;

import java.awt.Paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;;

public class FXFakePaint extends Application{

	@Override
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		VBox vBox = new VBox();
		//the label for the scroll bar
		Text scrollBarLabel = new Text("Size of Circle");
		
		CheckBox erasure = new CheckBox();
		
		//Create and define the values of a scrollbar
		ScrollBar sc = new ScrollBar();
		sc.setMin(1);
		sc.setMax(50);
		sc.setValue(20);
		
		ColorPicker colorPicker = new ColorPicker();
		//what you draw on
		Canvas canvas = new Canvas(400,400);	
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//Creates a circle on mouse click
		canvas.setOnMousePressed(e ->{		
		if(e.getButton() == MouseButton.PRIMARY && !erasure.isSelected()) {
				drawCircle(colorPicker,gc,e,sc);		
			}
		else {
			erase(gc,e,sc);
			
		}
			
		});
		//when the mouse is dragged it creates circles following that line
		canvas.setOnMouseDragged(e -> {
			
			if(e.getButton() == MouseButton.PRIMARY && !erasure.isSelected()) {
				drawCircle(colorPicker,gc,e,sc);
		}
			else {
				erase(gc,e,sc);
			}
			
		});
		
		vBox.getChildren().addAll(colorPicker,scrollBarLabel,sc,erasure);
		
		pane.setCenter(canvas);
		pane.setLeft(vBox);
		
		Scene scene = new Scene(pane);
		
		primaryStage.setTitle("FakePaint");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
	public void drawCircle(ColorPicker colorPicker,GraphicsContext gc,MouseEvent e,ScrollBar sc) {
		gc.setFill(colorPicker.getValue());
		gc.fillOval(e.getX() , e.getY(), sc.getValue(), sc.getValue());
	}
	
	public void erase(GraphicsContext gc,MouseEvent e,ScrollBar sc) {
		gc.setFill(Color.WHITE);
		gc.fillOval(e.getX() , e.getY(), sc.getValue(), sc.getValue());

	}
	
}
