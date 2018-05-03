package application;
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

import java.util.List;
import javafx.stage.Stage;

/**
 * Represents a tournament in which 0, 1, 2, 4, 8, 16 (or greater powers of 2, up to 64 tested successfully)
 * teams can participate.
 */
public class Tournament {
	private Game[] games;													// Keep track of all the games that will occur in the tournament
	private GUI gui;														// A reference to the GUI that displays the tournament
	private String solo;													// The name of the winner if there is only 1 team
	private int challengers;												// The number of challengers (or teams) participating

	/**
	 * @param list, the list of teams that will participate in the tournament
	 * @param primaryStage, the stage on which to display the tournamnent (passed to and used by the GUI class)
	 * Constructs a tournament from the list of team names
	 * Calls the constructor for a GUI to display on primaryStage
	 */
	public Tournament (List<String> list, Stage primaryStage) {
		challengers = list.size();
		games = populateGames(list);
		if(list.size() == 0) {												// Empty list, winner "None"
			solo = "None";
		}
		if (list.size() == 1) {
			solo = list.get(0);												// Single team on list, solo winner
		}
		gui = new GUI(primaryStage, this, games, solo, challengers);
	}

	/**
	 * @param list, the list of team names that will be used to populate the tournament
	 * Creates all instances of games from the list of teams
	 */
	private Game[] populateGames(List<String> list) {
		if (list.size() == 0 || list.size() == 1) {							// List has 0 or 1 teams -> Skip to end
			return null;
		}
		Game[] tempGames = new Game[list.size() - 1];
		int rank = 0;
		Challenger[] challengers = new Challenger[list.size()];
		for (String s : list) {												// Create an array of Games from list of team names
			challengers[rank] = new Challenger("#" + (rank + 1) + " " + s);
			rank++;
		}
		for (int i = 0; i < list.size()/2; i++) {										// Swap teams so that highly ranked teams first face
			tempGames[i] = new Game(challengers[i], challengers[rank - i - 1], this);	//  their lowly ranked counterparts (i.e. #1 vs #8, #2 vs #7)
			tempGames[i].setIndex(i);
		}																
		for (int i = list.size()/2; i < tempGames.length; i++) {
			tempGames[i] = new Game(this);
			tempGames[i].setIndex(i);
		}
		for (int i = 0; i < tempGames.length/4 + 1; i++) {					// Swap games so that highly ranked 
			if ((i % 2) > 0) {												// teams meet at the end
				Game temp = tempGames[i];
				tempGames[i] = tempGames[tempGames.length/2 - i + 1];
				tempGames[tempGames.length/2 - i + 1] = temp;
				tempGames[i].setIndex(i);														// Re-establish correct indexes
				tempGames[tempGames.length/2 - i + 1].setIndex(tempGames.length/2 - i + 1);		// ^
			}
		}
		return tempGames;
	}

	/**
	 * @param gamePos, the index of the game calling this method from the games array
	 * Sends the winner of a game at index gamePos to the next round/game
	 */
	public void nextRound(int gamePos) {
		if((challengers + gamePos)/2 >= games.length) {						// Winner found
			gui.displayTopThree(null);
			return;
		}
		double nextGame = ((double)challengers + gamePos)/2.0;
		if (nextGame - Math.floor(nextGame) > 0) {							// Put winner at bottom of next game display
			games[(challengers + gamePos)/2].setc2(games[gamePos].getWinner());
		} else {															// Put winner at top of next game display
			games[(challengers + gamePos)/2].setc1(games[gamePos].getWinner());
		}
		gui.update((int)nextGame);
	}

	/**
	 * @return the string of the 3rd place team name
	 * Computes and returns the string of the 3rd place winner
	 */
	public String getThird() {
		if (games.length >= 3) {											// Only if there were at least 3 game can we have a 3rd place
			return (games[games.length - 2].getLoserScore() > games[games.length - 3].getLoserScore() ?
					games[games.length - 2].getLoser().name : games[games.length - 3].getLoser().name);
		} else {
			return ("None");
		}
	}
}
