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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class, starts the tournament program. Constructs a tournament from a list of challenger/team names
 * read from a file whose name is entered as a single command-line argument.
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String filename = scan.nextLine();							// Read a single command line to get the file name
		File file = new File(filename);
		List<String> list;
		try {
			list = FileReader.parseFile(file.getAbsolutePath());
			new Tournament(list, primaryStage);						// Construct the tournament from the list of that file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
