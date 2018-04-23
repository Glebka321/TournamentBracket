import java.util.List;

import javafx.stage.Stage;

public class Tournament {
    
    private Game[] games;
    private GUI gui;
    
    public Tournament (List<String> list, Stage primaryStage) {
        games = populateGames(list);
        gui = new GUI(primaryStage, this, games);
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
        	//System.out.println(rank + "  " + i +  "  " + list.size());
            game[i] = new Game(challengers[i], challengers[rank - i - 1]);
        }  
        return game;//swap(game);
    }
    /**
     * Adjust the tournament bracket
     * @param games
     * @return
     */
    private Game[] swap(Game[] game) {
        int cnt = 0;
        for (int i = 1; i < game.length / 2; i += 2) {
            Game tmp = game[i];
            game[i] = game[game.length - cnt];
            game[game.length - cnt] = tmp;
            cnt += 2;
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
        games = nextRound;
        gui.nextRound(games);
    }
   
    /*
    private GUI makeGUI() {
        
    }*/
    
}
