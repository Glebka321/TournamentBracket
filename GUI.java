/**
 * Title:		Tournament
 * Files:		Main.java, Tournament.java, GUI.java, Game.java, Challenger.java
 * Course:		CS400 Spring 2018
 * 
 * Bugs:		Handles up to 2^6 teams, do not use 2^7
 * 
 * @author		Michael White, Haocheng Chen, Daniel Miller, Gleb Tsyganov
 * Email:		mswhite3@wisc.edu, hwang663@wisc.edu, ddmiller3@wisc.edu, tsyganov@wisc.edu
 * Instructor:	Professor Deppeler
 * 
 * Date:		May 5, 2018
 */

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Creates a GUI display for the tournament class complete with team labels, submit buttons,
 * user feedback, and winner leaderboard display
 */
public class GUI {
	private Scene scene;													// Current scene
	private GridPane gpane;													// Everything displayed on a GridPane
	private Game[] games;													// Keep track of all the games that will occur in the tournament
	private int challengers;												// The number of challengers (or teams) participating
	private Tournament tourney;												// A reference to the tournament object that uses the GUI
	private int length;														// The number of games in the tournament

	/**
	 * @param primaryStage, the stage on which to display the gui
	 * @param games, keep track of all the games that will occur in the tournament
	 * @param solo, only used if there were only 0 or 1 teams in the read data file
	 * @param challengers, the number of teams that wil participate in the tournament
	 * Constructs a GUI for the tournament using a gridpane to display challengers, their buttons, feedback,
	 * and winner display
	 */
	public GUI(Stage primaryStage, Tournament tourney, Game[] games, String solo, int challengers) {
		this.tourney = tourney;
		this.length = (games != null ? games.length : 0);
		this.challengers = challengers;
		this.games = games;
		gpane = new GridPane();
		scene = new Scene(gpane);
		setColumns();														// Set Column and row constraints so 
		setRows();															// the gui buttons and labels display nicely

		if (solo != null) {													// If there is a solo winner, skip to the end
			displayTopThree(solo);
		} else {															// Otherwise continue
			setup();	
		}
		
		gpane.add(new Label("First place: "), 0, challengers*3/2);
		gpane.add(new Label("Second place: "), 0, challengers*3/2 + 1);
		gpane.add(new Label("Third place: "), 0, challengers*3/2 + 2);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	/**
	 * Alternates column widths between 30 and 80 pixels
	 * 80 for team name labels
	 * 30 for input boxes
	 * Called only by the constructor
	 */
	private void setColumns() {
		for (int i = 0; i < length*4; i++) {
			if (i % 2 > 0) {
				ColumnConstraints column = new ColumnConstraints(30);
				gpane.getColumnConstraints().add(column);
			} else {
				ColumnConstraints column = new ColumnConstraints(80);
				gpane.getColumnConstraints().add(column);
			}
		}

	}

	/**
	 * Sets all used row heights to 30
	 * Called only by the constructor
	 */
	private void setRows() {
		for (int i = 0; i < challengers*3/2 + 3; i++) {
			RowConstraints row = new RowConstraints(30);
			gpane.getRowConstraints().add(row);
		}
	}

	/**
	 * Sets the GUI up with labels and input boxes to display the tournament
	 * Called only by the constructor
	 */
	private void setup() {
		int numRounds = (int) Math.log(challengers) + 2;						// Logarithmic regression to determine the number of rounds to final
		int y = challengers/2;													// The number of games per round
		int gameCounter = 0;
		for(int x=0; x<numRounds; x++) {
			for(int i=0; i<y; i++) {
				// 4* ... one column for label, one for input, two space
				// 3* ... one row for c1, one for c2, one for submit button

				gpane.add(games[gameCounter].getc1Label(), 4*x, 3*i);			// Add Labels, one below the other
				gpane.add(games[gameCounter].getc2Label(), 4*x, 3*i+1);		

				gpane.add(games[gameCounter].getc1Input(), 4*x+1, 3*i);			// Add Input boxes, one below the other
				gpane.add(games[gameCounter].getc2Input(), 4*x+1, 3*i+1);

				gpane.add(games[gameCounter].getButton(), 4*x, 3*i+2);			// Then add submit button
				gameCounter++;
			}
			y/=2;																// The next round will have half as many games
		}
	}

	/**
	 * @param gamePos, the index of the game in the games array that called this method
	 * Updates the GUI to display the winner of a submitted game
	 */
	public void update(int gamePos) {
		Game game = games[gamePos];
		if(game.getChallengers()[0] != null) {
			game.getc1Label().setText(game.getChallengers()[0].name);			// Display c1 if just added
		}
		if(game.getChallengers()[1] != null) {
			game.getc2Label().setText(game.getChallengers()[1].name);			// Display c2 if just added
		}
	}

	/**
	 * @param solo, only used if there were 0 or 1 teams specified in the read data file
	 * Finds and displays the winner, second place, and third place (if applicable)
	 */
	public void displayTopThree(String solo) {
		if (length == 0) {														// There wasn't a tournament
			gpane.add(new Label(solo != null ? solo : "None"), 2, challengers*3/2);		//Either 0 or 1 winners
			gpane.add(new Label("None"), 2, challengers*3/2 + 1);
			gpane.add(new Label("None"), 2, challengers*3/2 + 2);
		} else {																// There was a tournament
			gpane.add(new Label(this.games[length - 1].getWinner().getName()), 2, challengers*3/2);
			gpane.add(new Label(this.games[length - 1].getLoser().getName()), 2, challengers*3/2 + 1);
			gpane.add(new Label(tourney.getThird()), 2, challengers*3/2 + 2);	// Note that there will only be a third if there were at least 3 games
		}
	}
}
