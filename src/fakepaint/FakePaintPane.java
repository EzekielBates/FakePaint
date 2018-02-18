package fakepaint;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FakePaintPane extends BorderPane{
	
	final Background vBoxBackground = new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null));
	final Background menuContainerBackground = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	
	//Everything that goes inside of the left side panel
	private ColorPicker colorPicker = new ColorPicker();
	private ScrollBar brushSize = new ScrollBar();
	private CheckBox erasure = new CheckBox();
	
	//everything that goes with the top file bar
	private MenuBar fileBar = new MenuBar();
	private Menu file = new Menu("File");
	private MenuItem open = new MenuItem("Open");
	private MenuItem save = new MenuItem("Save");
	private MenuItem newFakePaint = new MenuItem("New");
	
	//The canvas that will be drawn on
	private  Canvas canvas = new Canvas();
	private  GraphicsContext gc = canvas.getGraphicsContext2D();
	
	//constructs the pane 
	protected FakePaintPane(double width,double height){
		brushSize.setMin(1);		
		setLeft(createLeftPanel());
		setTop(createMenuBar());
		canvas.setHeight(height);
		canvas.setWidth(width);
		setCenter(canvas);
	}
	//creates the left pane in the window
	private VBox createLeftPanel() {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(this.colorPicker,new Text("Brush Size"),this.brushSize,new Text("Erasure"),this.erasure);
		vBox.setBackground(vBoxBackground);
		return vBox;
	}
	//creates the top file menu bar 
	private HBox createMenuBar() {
		HBox hBox = new HBox();
		this.file.getItems().addAll(newFakePaint,open,save);
		this.fileBar.getMenus().add(file);
		hBox.getChildren().add(this.fileBar);
		hBox.setBackground(menuContainerBackground);
		return hBox;
		
	}
	
	public ScrollBar getBrushSize() {
		return brushSize;
	}
	
	public ColorPicker getColorPicker() {
		return colorPicker;
	}
	
	public CheckBox getErasure() {
		return erasure;
	}
	public MenuBar getFileBar() {
		return fileBar;
	}
	public Menu getFile() {
		return file;
	}
	public MenuItem getOpen() {
		return open;
	}
	public MenuItem getSave() {
		return save;
	}
	public MenuItem getNewFakePaint() {
		return newFakePaint;
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void draw(MouseEvent e) {
		gc.setFill(colorPicker.getValue());
		gc.fillOval(e.getX() - this.brushSize.getValue()/2, e.getY() - this.brushSize.getValue()/2, this.brushSize.getValue(), this.brushSize.getValue());
	}
	
	public void erase(MouseEvent e) {
		gc.setFill(Color.WHITE);
		gc.fillOval(e.getX() - this.brushSize.getValue()/2, e.getY() - this.brushSize.getValue()/2, this.brushSize.getValue(), this.brushSize.getValue());
	}
	
}
