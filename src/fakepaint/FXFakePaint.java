package fakepaint;


import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.sun.istack.internal.logging.Logger;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;;

public class FXFakePaint extends Application{

	final Background vBoxBackground = new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null));
	final Background menuContainerBackground = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		VBox vBox = new VBox();
		//sets the background color and insets of the vbox to gray
		vBox.setBackground(vBoxBackground);
		vBox.setPadding(new Insets(10));
		//creating an HBox to store the menu and changing its backgroung color.
		HBox menuContainer = new HBox();
		menuContainer.setBackground(menuContainerBackground);
		//the label for the scroll bar
		Text scrollBarLabel = new Text("Size of Circle");
		Text erasureText = new Text("Erasure");
		
		//createing a menuBar
		MenuBar menuBar = new MenuBar();
		menuBar.setBackground(menuContainerBackground);
		Menu file = new Menu("File");
		MenuItem newCanvas = new MenuItem("New");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");

		
		//Putting the menuBar together. 	
		file.getItems().addAll(newCanvas,open,save);		
		menuBar.getMenus().add(file);
		
		//adding the items to the menuContainer 		
		menuContainer.getChildren().addAll(menuBar);
		
		//CheckBox to set if you want to erase or not.
		CheckBox erasure = new CheckBox();
		
		//Create and define the values of a scrollbar
		ScrollBar sc = new ScrollBar();
		sc.setMin(1);
		sc.setMax(50);
		sc.setValue(20);
		//How you select what color you want to draw in.
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
		
		save.setOnAction(e ->{
			saveImage(canvas,primaryStage);
		});
		
		vBox.getChildren().addAll(colorPicker,scrollBarLabel,sc,erasureText,erasure);
		
		pane.setCenter(canvas);
		pane.setLeft(vBox);
		pane.setTop(menuContainer);
		
		Scene drawingScene = new Scene(pane);
		
		primaryStage.setTitle("FakePaint");
		primaryStage.setScene(drawingScene);
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
	
	public void saveImage(Canvas canvas,Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		
		//set the extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extFilter);
		
		//show save file dialog
		File file = fileChooser.showSaveDialog(primaryStage);
		
		if(file != null) {
			try {
				WritableImage writableImage = new WritableImage((int)canvas.getWidth(),(int)canvas.getHeight());
				canvas.snapshot(null, writableImage);
				RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage,null);
				ImageIO.write(renderedImage, "png", file);
			}catch(IOException ex){
				Logger.getLogger(FXFakePaint.class.getName(), null).log(Level.SEVERE,null,ex);
			}
		}
	}
	
}
