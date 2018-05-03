import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Game {
	Challenger challenger1;
	Challenger challenger2;
	int[] finalScores = new int[2];
	Tournament tourney;
	int index;
	
	Button button;
	Label c1Label;
	Label c2Label;
	TextField c1Input;
	TextField c2Input;
	
	public Game(Tournament tourney, int index) {
		button = new Button("Submit");
		c1Label = new Label();
		c2Label = new Label();
		c1Input = new TextField();
		c2Input = new TextField();
		this.tourney = tourney;
		this.index = index;
		
		buildButton();
	}
	
	public Game (Challenger challenger1, Challenger challenger2, Tournament tourney, int index) {
		this.challenger1 = challenger1;
		this.challenger2 = challenger2;
		button = new Button("Submit");
		c1Label = new Label(challenger1.getName());
		c2Label = new Label(challenger2.getName());
		c1Input = new TextField();
		c2Input = new TextField();
		this.tourney = tourney;
		this.index = index;
		
		buildButton();
	}
	
	void buildButton() {
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(isNumeric(getc1Input().getText()) &&
						isNumeric(getc2Input().getText())) {
					setFinalScores(Integer.parseInt(getc1Input().getText()), Integer.parseInt(getc2Input().getText()));
					tourney.nextRound(index);
				} else {
					//MIKE SHOW ALERT THAT THE INPUT NEEDS TO BE NUMBERS
				}
			} 
		});
	}
	
	private boolean isNumeric(String str) {  
		try  {  
			int d = Integer.parseInt(str);  
		} catch(NumberFormatException nfe)  {  
			return false;  
		}  
		return true;  
	}
	
	void setFinalScores(int score1, int score2) {
		finalScores[0] = score1;
		finalScores[1] = score2;
	}
	
	public Challenger getWinner() {
		return (finalScores[0] > finalScores[1] ? challenger1 : challenger2);
	}

	public Challenger getLoser() {
		return (finalScores[0] > finalScores[1] ? challenger2 : challenger1);
	}

	public Challenger[] getChallengers() {
		return new Challenger[] {challenger1, challenger2};
	}
	
	public void setc1(Challenger c1) {
		challenger1 = c1;
	}
	
	public void setc2(Challenger c2) {
		challenger2 = c2;
	}
	
	public int getLoserScore() {
		return (finalScores[0] > finalScores[1] ? finalScores[1] : finalScores[0]);
	}
	
	public Button getButton() {
		return button;
	}
	
	public Label getc1Label() {
		return c1Label;
	}
	
	public Label getc2Label() {
		return c2Label;
	}
	
	public TextField getc1Input() {
		return c1Input;
	}
	
	public TextField getc2Input() {
		return c2Input;
	}
}
