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
