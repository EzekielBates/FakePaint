package fakepaint;

import filemanagement.FileManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;;

public class FXFakePaint extends Application{

	final Background vBoxBackground = new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null));
	final Background menuContainerBackground = new Background(new BackgroundFill(Color.LIGHTGRAY,null,null));
	FileManager fileManager = new FileManager();
	
	@Override
	public void start(Stage primaryStage) {
		
		FakePaintPane pane = new FakePaintPane(500,500);
		
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
		
		pane.getSave().setOnAction(e -> {
			fileManager.saveImage(pane.getCanvas(), primaryStage);
		});
		
		Scene drawingScene = new Scene(pane);
		
		primaryStage.setTitle("FakePaint");
		primaryStage.setScene(drawingScene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
	
	
	
}
