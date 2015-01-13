package napakalakiGame;

public class Monster {
    //Variables
    private String name;
    private int level;
    private Prize prize;
    private BadConsequence bc;

    //Constructor
    public Monster(String name, int level, Prize prize, BadConsequence bc) {
        this.name = name;
        this.level = level;
        this.prize = prize;
        this.bc = bc;
    }

    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return level;
    }

    public BadConsequence getBadConsequence() {
        return bc;
    }
    /*Devuelve el número de niveles ganados proporcionados por su buen rollo.*/
    public int getLevelsGained() {
        return prize.getLevel();
    }
    /*Devuelve el número de tesoros ganados proporcionados por su buen rollo.*/
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    /*Devuelve true cuando el mal rollo del monstruo es muerte y false en caso contrario.*/
    public boolean kills(){
        if(bc.myBadConsequenceIsDeath()){
            return true;
        }else{
            return false;
        }
    }
    
    public Prize getPrize(){
        return prize;
    }

    public String toString() {
        return  "Nombre : " + name + "  Nivel : " + level + "\nPrize :" + " Nivel " + prize.getLevel() + " Tesoros " + prize.getTreasures() + "\nbc : " + "Nivel " + bc.getLevel() + " Visibles " + bc.getNVisibleTreasures() + " Ocultos " + bc.getNHiddenTreasures();
    }
    
}
