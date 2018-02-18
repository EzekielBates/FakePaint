package filemanagement;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.sun.istack.internal.logging.Logger;

import fakepaint.FXFakePaint;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileManager {

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
	
	public void openImage() {}
	
}
