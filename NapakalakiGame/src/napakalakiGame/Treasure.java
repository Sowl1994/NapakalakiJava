package napakalakiGame;

public class Treasure {
    private String name;
    private int goldCoins;
    private int minBonus;
    private int maxBonus;
    private TreasureKind tk;

    public Treasure(String name, int goldCoins, int minBonus, int maxBonus) {
        this.name = name;
        this.goldCoins = goldCoins;
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
    }
    
    public Treasure(String name, int goldCoins, int minBonus, int maxBonus, TreasureKind tk) {
        this.name = name;
        this.goldCoins = goldCoins;
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
        this.tk = tk;
    }
    
    public String getName(){
        return name;
    }
    

    public int getGoldCoins() {
        return goldCoins;
    }


    public int getMinBonus() {
        return minBonus;
    }


    public int getMaxBonus() {
        return maxBonus;
    }


    public TreasureKind getType() {
        return tk;
    }

    public String toString() {
        return "Nombre : " + name + "\ngoldCoins : " + goldCoins + "\nminBonus : " + minBonus + "\nmaxBonus : " + maxBonus + "\ntk : " + tk;
    }

    
}
