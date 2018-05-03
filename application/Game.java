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

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Represents a game between two challengers in the tournament
 */
public class Game {
	private Challenger challenger1;						// the upper bracket challenger
	private Challenger challenger2;						// the lower bracket challenger
	private int[] finalScores = new int[2];				// the scores of the two challengers
	private Tournament tourney;							// a reference to the tournament object using this class
	private int index;									// the index of this game in the tournament's games array

	private Button button;								// the submit button
	private Label c1Label;								// challenger1's name label
	private Label c2Label;								// challenger2's name label
	private TextField c1Input;							// challenger1's input box
	private TextField c2Input;							// challenger2's input box

	/**
	 * @param tourney, a reference to the tournament object using this class
	 * Constructor used to create an empty game (awaiting challengers)
	 */
	public Game(Tournament tourney) {
		button = new Button("Submit");
		c1Label = new Label();
		c2Label = new Label();
		c1Input = new TextField();
		c2Input = new TextField();
		this.tourney = tourney;

		buildButton(false);								// Hide submit button until this game is ready
	}

	/**
	 * @param challenger1, the upper bracket challenger
	 * @param challenger2, the lower bracket challenger
	 * @param tourney, a reference to the tournament object using this class
	 * Constructor used to create a full game (first round game)
	 */
	public Game (Challenger challenger1, Challenger challenger2, Tournament tourney) {
		this.challenger1 = challenger1;
		this.challenger2 = challenger2;
		button = new Button("Submit");
		c1Label = new Label(challenger1.getName());
		c2Label = new Label(challenger2.getName());
		c1Input = new TextField();
		c2Input = new TextField();
		this.tourney = tourney;

		buildButton(true);								// This game is ready, waiting for input
	}

	/**
	 * @param show, boolean to determine if the button should be visible yet (true = yes, false = no)
	 * Sets an eventhandler for mouse clicks for the submit button
	 */
	void buildButton(boolean show) {
		button.setVisible(show);
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(isNumeric(getc1Input().getText()) &&
						isNumeric(getc2Input().getText())) {
					if (getc1Input().getText().equals(getc2Input().getText()) == false) {
						setFinalScores(Integer.parseInt(getc1Input().getText()), Integer.parseInt(getc2Input().getText()));
						tourney.nextRound(index);
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Whoops!");
						alert.setHeaderText("Ties are not allowed!");
						alert.showAndWait();
					}
				}
			} 
		});
	}

	/**
	 * @param str, the string from the input box
	 * @return true if the string is an integer, false if it is not
	 * Determines if the input box contains an integer. Sends an alert if it does not.
	 */
	private boolean isNumeric(String str) {  
		try  {  
			Integer.parseInt(str);
		} catch(NumberFormatException nfe)  {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Whoops!");
			alert.setHeaderText("Field Invalid: \"" + str + "\". Integers only!");
			alert.showAndWait();
			return false;  
		}  
		return true;
	}

	/**
	 * @param score1, challenger1's score
	 * @param score2, challenger2's score
	 * Sets the final scores of this game - now able to determine a winner. Removes the submit button
	 * and prevents any further edits to the scores
	 */
	void setFinalScores(int score1, int score2) {
		finalScores[0] = score1;
		finalScores[1] = score2;
		button.setVisible(false);
		c1Input.setEditable(false);
		c2Input.setEditable(false);
	}

	/**
	 * @return the winner of this game
	 */
	public Challenger getWinner() {
		return (finalScores[0] > finalScores[1] ? challenger1 : challenger2);
	}

	/**
	 * @return the loser of this game
	 */
	public Challenger getLoser() {
		return (finalScores[0] > finalScores[1] ? challenger2 : challenger1);
	}

	/**
	 * @return the challengers of this game
	 */
	public Challenger[] getChallengers() {
		return new Challenger[] {challenger1, challenger2};
	}

	/**
	 * @param c1, challenger1
	 * Sets challenger1 to c1
	 */
	public void setc1(Challenger c1) {
		challenger1 = c1;
		if (challenger1 != null && challenger2 != null)
			button.setVisible(true);
	}

	/**
	 * @param c2, challenger2
	 * Sets challenger2 to c2
	 */
	public void setc2(Challenger c2) {
		challenger2 = c2;
		if (challenger1 != null && challenger2 != null)
			button.setVisible(true);
	}

	/**
	 * @return the loser's score
	 * This is only used to determine the 3rd place winner
	 */
	public int getLoserScore() {
		return (finalScores[0] > finalScores[1] ? finalScores[1] : finalScores[0]);
	}

	/**
	 * @return the loser's score
	 */
	public Button getButton() {
		return button;
	}

	/**
	 * @return challenger1's name label
	 */
	public Label getc1Label() {
		return c1Label;
	}

	/**
	 * @return challenger2's name label
	 */
	public Label getc2Label() {
		return c2Label;
	}

	/**
	 * @return challenger1's input box
	 */
	public TextField getc1Input() {
		return c1Input;
	}

	/**
	 * @return challenger2's input box
	 */
	public TextField getc2Input() {
		return c2Input;
	}

	/**
	 * @param index, the index of this game in the tournament's games array
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}
