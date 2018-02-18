package fakepaint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;;

public class FXFakePaint extends Application{

	@Override
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		VBox vBox = new VBox();
		
		Text scrollBarLabel = new Text("Size of Circle");
		
		//Create and define the values of a scrollbar
		ScrollBar sc = new ScrollBar();
		sc.setMin(1);
		sc.setMax(50);
		sc.setValue(20);
		
		ColorPicker colorPicker = new ColorPicker();
		//what you draw on
		Canvas canvas = new Canvas(400,400);		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//allows you to draw on a mouse click
		canvas.setOnMouseDragged(e -> {
			
			if(e.getButton() == MouseButton.PRIMARY ) {
			gc.setStroke(colorPicker.getValue());
			gc.setFill(colorPicker.getValue());
			gc.fillOval(e.getX() , e.getY(), sc.getValue(), sc.getValue());
			gc.strokeOval(e.getX(), e.getY(), sc.getValue(), sc.getValue());
			
		}
			
		});
		
		vBox.getChildren().addAll(colorPicker,scrollBarLabel,sc);
		
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
	
}
