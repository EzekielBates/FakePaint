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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * 
 * @author Ezekiel Bates
 * Class that creates a borderPane loaded with all the neccessary
 * tools to run create a fake paint window.
 *
 */

public class FakePaintPane extends BorderPane{
	
	final Background vBoxBackground = new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null));
	final Background menuContainerBackground = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	final Background everythingBackground = new Background(new BackgroundFill(Color.GRAY,null,null));
	final Border canvasBorder = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY, BorderWidths.DEFAULT));
	
	//Everything that goes inside of the left side panel
	private ColorPicker colorPicker = new ColorPicker();
	private ScrollBar brushSize = new ScrollBar();
	private CheckBox erasure = new CheckBox();
	
	//everything that goes with the top file bar
	private MenuBar fileBar = new MenuBar();
	private Menu file = new Menu("File");
	private Menu options = new Menu("Options");
	private MenuItem open = new MenuItem("Open");
	private MenuItem save = new MenuItem("Save");
	private MenuItem newFakePaint = new MenuItem("New");
	private MenuItem changeCanvasSize = new MenuItem("Resize");

	//The canvas that will be drawn on
	private Pane canvasPane = new Pane();
	private  Canvas canvas = new Canvas();
	private  GraphicsContext gc = canvas.getGraphicsContext2D();
	
	/**
	 * constructs the pane. 
	 * @param width the width of the canvas
	 * @param height the height of the canvas
	 */
	protected FakePaintPane(double width,double height){
		setUpCanvasPane(height,width);
		brushSize.setMin(1);
		brushSize.setValue(1);
		setLeft(createLeftPanel());
		setTop(createMenuBar());	
		setCenter(canvasPane);
	}
	/**
	 * Create the left pane for the fake paint GUI.
	 * @return returns the VBox that creates the left pane in the window
	 */
	private VBox createLeftPanel() {
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(this.colorPicker,new Text("Brush Size"),this.brushSize,new Text("Erasure"),this.erasure);
		vBox.setBackground(vBoxBackground);
		return vBox;
	}
	/**
	 * creates the top file menu bar for the fake paint GUI.
	 * @return returns the HBox that has all of the fileMenu items in it.
	 */
	private HBox createMenuBar() {
		HBox hBox = new HBox();
		this.file.getItems().addAll(newFakePaint,open,save);
		this.options.getItems().add(changeCanvasSize);
		this.fileBar.getMenus().addAll(file,options);
		hBox.getChildren().addAll(this.fileBar);
		hBox.setBackground(menuContainerBackground);
		return hBox;
		
	}
	
	private void setUpCanvasPane(double height,double width) {
		canvas.setWidth(width);
		canvas.setHeight(height);
		canvasPane.getChildren().add(canvas);
		canvasPane.setBorder(canvasBorder);	
		canvasPane.setMaxHeight(height);
		canvasPane.setMaxWidth(width);
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
	public Pane getCanvasPane() {
		return canvasPane;
	}
	public MenuItem getChangeCanvasSize() {
		return changeCanvasSize;
	}
	
	/**
	 * This method creates a circle at the mouse point.
	 * @param e A MouseEvent
	 */
	public void draw(MouseEvent e) {
		gc.setFill(colorPicker.getValue());
		gc.fillOval(e.getX() - this.brushSize.getValue()/2, e.getY() - this.brushSize.getValue()/2, this.brushSize.getValue(), this.brushSize.getValue());
	}
	/**
	 * This method creates a white circle to 'erase' whatever is drawn on the canvas at the mouse point.
	 * @param e A MouseEvent
	 */
	public void erase(MouseEvent e) {
		gc.setFill(Color.WHITE);
		gc.fillOval(e.getX() - this.brushSize.getValue()/2, e.getY() - this.brushSize.getValue()/2, this.brushSize.getValue(), this.brushSize.getValue());
	}
	
	public void resizeWindow(double width,double height) {
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
	}
	
}
