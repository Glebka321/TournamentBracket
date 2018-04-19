import java.util.List;

public class Tournament {
    
    private Game[] games;
    public Tournament (List<String> list) {
        games = populateGames(list);
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
        for (int i = 0; i < list.size() / 2; i++) {
            game[i] = new Game(challengers[i], challengers[rank - i]);
        }  
        return swap(game);
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
    public Game[] nextRound() {
        Game[] nextRound = new Game[games.length];
        int gameCounter = 0;
        for (int i = 0; i < games.length / 2; i++) {
            nextRound[i] = new Game(games[gameCounter].getWinner(), games[gameCounter + 1].getWinner());
            gameCounter += 2;
        } 
        games = nextRound;
        return games;
    }
   
    /*
    private GUI makeGUI() {
        
    }*/
    
}
