public class Game {
	Challenger challenger1;
	Challenger challenger2;
	int[] finalScores = new int[2]; 

	public Game (Challenger challenger1, Challenger challenger2) {
		this.challenger1 = challenger1;
		this.challenger2 = challenger2;
	}

	void setFinalScores(int score1, int score2) {
		finalScores[0] = score1;
		finalScores[1] = score2;
	}

	public Challenger getWinner() {
		if (finalScores[0] > finalScores[1])
			return challenger1;
		return challenger2;
	}

	public Challenger getLoser() {
		if (finalScores[0] > finalScores[1])
			return challenger2;
		return challenger1;
	}

	public Challenger[] getChallengers() {
		return new Challenger[] {challenger1, challenger2};
	}
}
