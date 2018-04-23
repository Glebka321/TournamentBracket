import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class GUI {
	Stage primaryStage;
	Scene scene;
	GridPane gpane;
	Button next = new Button();
	Game[] games;

	Tournament tourney;
	int currentRound = -1;
	int length;

	public GUI(Stage primaryStage, Tournament tourney, Game[] games) {
		this.primaryStage = primaryStage;
		gpane = new GridPane();
		scene = new Scene(gpane, 1000, 500);
		this.length = games.length;

		nextRound(games);

		primaryStage.setScene(scene);
		primaryStage.show();
		next.setPrefWidth(100);
		next.setText("Next Round");

		gpane.add(next, 0, 13);
		gpane.add(new Label("First place: "), 2, 13);
		gpane.add(new Label("Second place: "), 2, 14);
		gpane.add(new Label("Third place: "), 2, 15);

		for (int i = 0; i < games.length*4 - 1; i++) {
			if (i % 2 > 0) {
				ColumnConstraints column = new ColumnConstraints(30);
				gpane.getColumnConstraints().add(column);
			} else {
				ColumnConstraints column = new ColumnConstraints(80);
				gpane.getColumnConstraints().add(column);
			}
		}
		for (int i = 0; i < 13; i++) {
			RowConstraints row = new RowConstraints(30);
			gpane.getRowConstraints().add(row);
		}

		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				for (int y = 0; y < Math.ceil(getGames().length/2); y++) {
					TextField t1 = (TextField) getNode(currentRound*2 + 1, y*3);
					TextField t2 = (TextField) getNode(currentRound*2 + 1, y*3 + 1);
					getGames()[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
				}
				for (int y = (int) Math.ceil(getGames().length/2); y < getGames().length; y++) {
					TextField t1 = (TextField) getNode((int)(6*Math.log(length)) + 1 - currentRound*2, (y - (int) Math.ceil(getGames().length/2))*3);
					TextField t2 = (TextField) getNode((int)(6*Math.log(length)) + 1 - currentRound*2, (y - (int) Math.ceil(getGames().length/2))*3 + 1);
					getGames()[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
				}
				tourney.nextRound();
			} 
		});
	}

	private Game[] getGames() {
		return games;
	}

	private Node getNode(int col, int row) {
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
		for (int y = 0; y < Math.ceil(games.length/2); y++) {
			Label c1 = new Label();
			c1.setText(games[y].getChallengers()[0].getName());
			c1.setStyle("-fx-background-color: #FFAAAA; -fx-font: 12px \"Arial\"");
			TextField t1 = new TextField();

			Label c2 = new Label();
			c2.setText(games[y].getChallengers()[1].getName());
			c2.setStyle("-fx-background-color: #AAAAFF; -fx-font: 12px \"Arial\"");
			TextField t2 = new TextField();

			gpane.add(c1, currentRound*2, y*3);
			gpane.add(t1, currentRound*2 + 1, y*3);
			gpane.add(c2, currentRound*2, y*3 + 1);
			gpane.add(t2, currentRound*2 + 1, y*3 + 1);
		}
		for (int y = (int) Math.ceil(games.length/2); y < games.length; y++) {
			Label c1 = new Label();
			c1.setText(games[y].getChallengers()[0].getName());
			c1.setStyle("-fx-background-color: #FFAAAA; -fx-font: 12px \"Arial\"");
			TextField t1 = new TextField();

			Label c2 = new Label();
			c2.setText(games[y].getChallengers()[1].getName());
			c2.setStyle("-fx-background-color: #AAAAFF; -fx-font: 12px \"Arial\"");
			TextField t2 = new TextField();

			//6*ln(games.length) determined using a logarithmic regression on points (2, 4) (4, 8) (8, 12)
			System.out.println(currentRound);
			gpane.add(c1, (int)(6*Math.log(length)) - currentRound*2, (y - (int) Math.ceil(games.length/2))*3);
			gpane.add(t1,(int)(6*Math.log(length)) - currentRound*2 + 1, (y - (int) Math.ceil(games.length/2))*3);
			gpane.add(c2, (int)(6*Math.log(length)) - currentRound*2, (y - (int) Math.ceil(games.length/2))*3 + 1);
			gpane.add(t2,(int)(6*Math.log(length)) - currentRound*2 + 1, (y - (int) Math.ceil(games.length/2))*3 + 1);
		}
	}

	private void displayTopThree(Game[] games) {

	}
}
