import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		File file = new File("src/Data1.ssd");
		List<String> list;
		try {
			list = FileReader.parseFile(file.getAbsolutePath());
			Tournament tourney = new Tournament(list);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
