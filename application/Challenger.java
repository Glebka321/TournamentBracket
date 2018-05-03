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

/**
 * Represents a challenger participating in a tournament
 */
public class Challenger {
    String name;						// The name of the challenger/team
    
    /**
     * Constructor that simply assigns the challenger name
     */
    public Challenger(String name) {
        this.name = name;
    }
    
    /**
     * @return the name of this challenger
     */
    public String getName() {
        return name;
    }
}
