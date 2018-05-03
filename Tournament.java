import java.util.List;

import javafx.stage.Stage;

public class Tournament {

	private Game[] games = new Game[0];
	private Game[] finale;
	private Game[] semifinal;
	private GUI gui;
	private String solo; //only one team in read file
	private int challengers;

	public Tournament (List<String> list, Stage primaryStage) {
		challengers = list.size();
		games = populateGames(list);
		if(list.size() == 0) {
			solo = "None";
		}
		gui = new GUI(primaryStage, this, games, solo, challengers);
		
		if (list.size() == 1) {
			solo = list.get(0);
			gui.displayTopThree(games, solo);
			return;
		}
	}
	/**
	 * Creates new instances of games from the list of teams
	 * @param list
	 */
	private Game[] populateGames(List<String> list) {
		if (list.size() == 0) {
			return null;
		}
		Game[] tempGames = new Game[list.size() - 1];
		int rank = 0;
		Challenger[] challengers = new Challenger[list.size()];
		for (String s : list) {
			challengers[rank] = new Challenger(s);
			rank++;
		}
		for (int i = 0; i < list.size()/2; i++) {
			tempGames[i] = new Game(challengers[i], challengers[rank - i - 1], this, i); //debug
		}
		for (int i = list.size()/2; i < tempGames.length; i++) {
			tempGames[i] = new Game(this, i); //debug
		}
		return tempGames;
	}

	/**
	 * Creates a new round of games
	 * @return
	 */
	public void nextRound(int gamePos) {
		if((challengers + gamePos)/2 >= games.length) {
			gui.displayTopThree(games, null);
			return;
		}
		
		double nextGame = ((double)challengers + gamePos)/2.0;
		if (nextGame - Math.floor(nextGame) > 0) {	// bottom
			games[(challengers + gamePos)/2].setc2(games[gamePos].getWinner());
		} else {									// top
			games[(challengers + gamePos)/2].setc1(games[gamePos].getWinner());
		}
		
		gui.update((int)nextGame);

		
//		Game[] nextRound = new Game[games.length/2];
//		for (int i = 0; i < games.length-1; i+=2) {
//			nextRound[i/2] = new Game(games[i].getWinner(), games[i + 1].getWinner());
//		} 
//		semifinal = finale;
//		finale = games;
//		games = nextRound;
//		gui.nextRound(games);
	}

	public String getThird() {
		if(games[games.length - 2].getLoserScore() > games[games.length - 3].getLoserScore()) {
			return games[games.length - 2].getLoser().name;
		} else {
			return games[games.length - 3].getLoser().name;
		}
	}
	
//	public void update(int gamePos) {
//		gui.update(gamePos);
//	}
}
