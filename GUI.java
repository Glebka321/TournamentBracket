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
	Game[] games;
	int challengers;

	Tournament tourney;
//	int currentRound = -1;
	int length;

	public GUI(Stage primaryStage, Tournament tourney, Game[] games, String solo, int challengers) {
		this.tourney = tourney;
		this.primaryStage = primaryStage;
		gpane = new GridPane();
		scene = new Scene(gpane, 1000, 500);
		
		if (solo != null) {
			displayTopThree(new Game[0], solo);
			Label emptyState = new Label("Empty Tournament!");
			gpane.add(emptyState, 0, 0);
			primaryStage.setScene(scene);
			primaryStage.show();
			return;
		} else {
			setup();	
		}
		
		this.length = games.length;
		this.challengers = challengers;
		this.games = games;

		

		primaryStage.setScene(scene);
		primaryStage.show();
//		next.setPrefWidth(100);
//		next.setText("Next Round");

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

//		next.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				for (int y = 0; y < Math.ceil(getGames().length/2); y++) {
//					TextField t1 = (TextField) getNode(currentRound*2 + 1, y*3);
//					TextField t2 = (TextField) getNode(currentRound*2 + 1, y*3 + 1);
//					getGames()[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
//				}
//				for (int y = (int) Math.ceil(getGames().length/2); y < getGames().length; y++) {
//					TextField t1 = (TextField) getNode((int)(6*Math.log(length)) + 1 - currentRound*2, (y - (int) Math.ceil(getGames().length/2))*3);
//					TextField t2 = (TextField) getNode((int)(6*Math.log(length)) + 1 - currentRound*2, (y - (int) Math.ceil(getGames().length/2))*3 + 1);
//					getGames()[y].setFinalScores(Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()));
//				}
//				//tourney.nextRound();
//			} 
//		});
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

	public void update(int gamePos) {
		Game game = games[gamePos];
		if(game.getChallengers()[0] != null) {
			game.getc1Label().setText(game.getChallengers()[0].name);
		}
		if(game.getChallengers()[1] != null) {
			game.getc2Label().setText(game.getChallengers()[1].name);
		}
//		Button button = game.getButton();
	}
	
	private void setup() {
		int numRounds = (int) Math.log(challengers) + 1;
		
		//Use first & second column to display all of the games with input boxes
		int x = challengers/2;
		int gameCounter = 0;
		for(int y=0; y<numRounds; y++) {
			for(int i=0; i<x; i++) {
				gpane.add(games[gameCounter].getc1Label(), 2*y, 3*i);
				gpane.add(games[gameCounter].getc2Label(), 2*y, 3*i+1);
				
				gpane.add(games[gameCounter].getc1Input(), 2*y+1, 3*i);
				gpane.add(games[gameCounter].getc2Input(), 2*y+1, 3*i+1);
				
				gpane.add(games[gameCounter].getButton(), 2*y, 3*i+2);
				gameCounter++;
			}
			x/=2;
		}
	}
	

	
	
//	public void nextRound(Game[] games) {
//		if (games.length == 0)
//			displayTopThree(this.games, null);
//		this.games = games;
//		currentRound++;
//		for (int y = 0; y < Math.ceil(games.length/2); y++) {
//			Label c1 = new Label();
//			c1.setText(games[y].getChallengers()[0].getName());
//			c1.setStyle("-fx-background-color: #FFAAAA; -fx-font: 12px \"Arial\"");
//			TextField t1 = new TextField();
//
//			Label c2 = new Label();
//			c2.setText(games[y].getChallengers()[1].getName());
//			c2.setStyle("-fx-background-color: #AAAAFF; -fx-font: 12px \"Arial\"");
//			TextField t2 = new TextField();
//
//			gpane.add(c1, currentRound*2, y*3);
//			gpane.add(t1, currentRound*2 + 1, y*3);
//			gpane.add(c2, currentRound*2, y*3 + 1);
//			gpane.add(t2, currentRound*2 + 1, y*3 + 1);
//		}
//		for (int y = (int) Math.ceil(games.length/2); y < games.length; y++) {
//			Label c1 = new Label();
//			c1.setText(games[y].getChallengers()[0].getName());
//			c1.setStyle("-fx-background-color: #FFAAAA; -fx-font: 12px \"Arial\"");
//			TextField t1 = new TextField();
//
//			Label c2 = new Label();
//			c2.setText(games[y].getChallengers()[1].getName());
//			c2.setStyle("-fx-background-color: #AAAAFF; -fx-font: 12px \"Arial\"");
//			TextField t2 = new TextField();
//
//			//6*ln(games.length) determined using a logarithmic regression on points (2, 4) (4, 8) (8, 12)
//			gpane.add(c1, (int)(6*Math.log(length)) - currentRound*2, (y - (int) Math.ceil(games.length/2))*3);
//			gpane.add(t1,(int)(6*Math.log(length)) - currentRound*2 + 1, (y - (int) Math.ceil(games.length/2))*3);
//			gpane.add(c2, (int)(6*Math.log(length)) - currentRound*2, (y - (int) Math.ceil(games.length/2))*3 + 1);
//			gpane.add(t2,(int)(6*Math.log(length)) - currentRound*2 + 1, (y - (int) Math.ceil(games.length/2))*3 + 1);
//		}
//	}

	public void displayTopThree(Game[] games, String solo) {
		if (games.length == 0) {
			gpane.add(new Label(solo != null ? solo : "None"), 4, 13);
			gpane.add(new Label("None"), 4, 14);
			gpane.add(new Label("None"), 4, 15);
		} else {
			gpane.add(new Label(this.games[this.games.length - 1].getWinner().getName()), 4, 13);
			gpane.add(new Label(this.games[this.games.length - 1].getLoser().getName()), 4, 14);
			gpane.add(new Label(tourney.getThird() != null ? tourney.getThird() : "None"), 4, 15);
		}
	}
}
