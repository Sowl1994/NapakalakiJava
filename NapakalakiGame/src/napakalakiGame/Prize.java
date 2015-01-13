package napakalakiGame;

//Variables
public class Prize {
    private int treasures, level;

    //Constructor
    public Prize(int treasures, int level) {
        this.treasures = treasures;
        this.level = level;
    }
    
    public Prize(){
    
    }
    
    //Getter
    public int getTreasures() {
        return treasures;
    }

    public int getLevel() {
        return level;
    }
    
    //Mostramos esto:
    public String toString(){
        return "Treasures = " + Integer.toString(treasures)+ ", Levels = " + Integer.toString(level);
    }
}
