package fakepaint;

import filemanagement.FileManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;;

/**
 * 
 * @author Ezekiel Bates
 *The implementation class for fake paint.
 *
 */
public class FXFakePaint extends Application{

	final Background vBoxBackground = new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null));
	final Background menuContainerBackground = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	FileManager fileManager = new FileManager();
	
	@Override
	public void start(Stage primaryStage) {
		
		ResizeWindow resizeCanvas = new ResizeWindow();
		Stage secondaryStage = new Stage();

		FakePaintPane pane = new FakePaintPane(500,500);	
		
		Scene drawingScene = new Scene(pane);
		
		secondaryStage.setTitle("New FakePaint");
		secondaryStage.setScene(new Scene(resizeCanvas));
		
		primaryStage.setTitle("FakePaint");
		primaryStage.setScene(drawingScene);
		primaryStage.show();
		
		//draw and erase by clicking
		pane.getCanvas().setOnMouseClicked(e ->{
			if(e.getButton() == MouseButton.PRIMARY && !pane.getErasure().isSelected()) {
				pane.draw(e);		
			}
		else {
			pane.erase(e);			
		}
		});
		
		//draw and erase by dragging
		pane.getCanvas().setOnMouseDragged(e -> {
			if(e.getButton() == MouseButton.PRIMARY && !pane.getErasure().isSelected()) {
				pane.draw(e);		
			}
			else {
			pane.erase(e);			
		}
		});
		//save the current canvas drawing
		pane.getSave().setOnAction(e -> {
			fileManager.saveImage(pane.getCanvas(), primaryStage);
		});
		//resize the current canvas
		pane.getChangeCanvasSize().setOnAction(e->{
			secondaryStage.show();
			resizeCanvas.getBtEnter().setOnAction(a->{
				double width = Double.parseDouble(resizeCanvas.getWidthInput().getText());
				double height = Double.parseDouble(resizeCanvas.getHeightInput().getText());				
				pane.resizeWindow(width, height);
				pane.getCanvasPane().setMaxHeight(height);
				pane.getCanvasPane().setMaxWidth(width);
				secondaryStage.close();
			});
		});
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
