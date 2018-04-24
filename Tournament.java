import java.util.List;

import javafx.stage.Stage;

public class Tournament {

	private Game[] games;
	private Game[] finale;
	private Game[] semifinal;
	private GUI gui;
	private String solo;

	public Tournament (List<String> list, Stage primaryStage) {
		if (list.size() == 1)
			solo = list.get(0);
		games = populateGames(list);
		gui = new GUI(primaryStage, this, games, solo);
	}
	/**
	 * Creates new instances of games from the list of teams
	 * @param list
	 */
	private Game[] populateGames(List<String> list) {
		Game[] game = new Game[list.size() / 2];
		int rank = 0;
		Challenger[] challengers = new Challenger[list.size()];
		for (String s : list) {
			challengers[rank] = new Challenger(s);
			rank++;
		}
		for (int i = 0; i < list.size()/2; i++) {
			game[i] = new Game(challengers[i], challengers[rank - i - 1]);
		}
		return game;
	}

	/**
	 * Creates a new round of games
	 * @return
	 */
	public void nextRound() {
		Game[] nextRound = new Game[games.length/2];
		for (int i = 0; i < games.length-1; i+=2) {
			nextRound[i/2] = new Game(games[i].getWinner(), games[i + 1].getWinner());
		} 
		semifinal = finale;
		finale = games;
		games = nextRound;
		gui.nextRound(games);
	}

	public String getThird() {
		if (semifinal != null)
		return (semifinal[0].getLoserScore() > semifinal[1].getLoserScore() ?
				semifinal[0].getLoser().getName() : semifinal[1].getLoser().getName());
		else
			return null;
	}
}
