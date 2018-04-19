import java.awt.Color;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI {
	Stage primaryStage;
	Scene scene;
	GridPane gpane;
	Button next;

	Tournament tourney;
	Game[] games;
	int currentRound = 0;

	public GUI(Stage primaryStage, Tournament tourney, Game[] games) {
		this.primaryStage = primaryStage;
		gpane = new GridPane();
		scene = new Scene(gpane, 500, 1000);

		nextRound(games);
		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ObservableList<Node> children = gpane.getChildren();

				for (int y = 0; y < Math.ceil(games.length/4); y++) {
					TextField t1 = (TextField) getNode(currentRound*2 + 1, y*2);
					TextField t2 = (TextField) getNode(currentRound*2 + 1, y*2 + 1);
					games[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
				}
				for (int y = (int) Math.ceil(games.length/4); y < games.length/2; y++) {
					TextField t1 = (TextField) getNode(games.length*4 - 2 - currentRound*2, y*2);
					TextField t2 = (TextField) getNode(games.length*4 - 2 - currentRound*2, y*2 + 1);
					games[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
				}
				tourney.nextRound();
			} 
		});
	}

	private Node getNode(int row, int col) {
	    for (Node node : gpane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return node;
	        }
	    }
	    return null;
	}

	public void nextRound(Game[] games) {
		this.games = games;
		currentRound++;
		for (int y = 0; y < Math.ceil(games.length/4); y++) {
			Label c1 = new Label();
			c1.setText(games[y].getChallengers()[0].getName());
			TextField t1 = new TextField();

			Label c2 = new Label();
			c2.setText(games[y].getChallengers()[1].getName());
			TextField t2 = new TextField();

			gpane.add(c1, currentRound*2, y*2);
			gpane.add(t1, currentRound*2 + 1, y*2);
			gpane.add(c2, currentRound*2, y*2 + 1);
			gpane.add(t2, currentRound*2 + 1, y*2 + 1);
		}
		for (int y = (int) Math.ceil(games.length/4); y < games.length/2; y++) {
			Label c1 = new Label();
			c1.setText(games[y].getChallengers()[0].getName());
			TextField t1 = new TextField();

			Label c2 = new Label();
			c2.setText(games[y].getChallengers()[1].getName());
			TextField t2 = new TextField();

			gpane.add(c1, games.length*4 - 1 - currentRound*2, y*2);
			gpane.add(t1, games.length*4 - 2 - currentRound*2, y*2);
			gpane.add(c2, games.length*4 - 1 - currentRound*2, y*2 + 1);
			gpane.add(t2, games.length*4 - 2 - currentRound*2, y*2 + 1);
		}
	}

	private void displayTopThree(Game[] games) {

	}
}
