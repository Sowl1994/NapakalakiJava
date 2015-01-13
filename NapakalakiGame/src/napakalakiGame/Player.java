package napakalakiGame;

import java.util.ArrayList;

public class Player {
    private boolean dead = true;
    private String name;
    private int level=1;
    private BadConsequence pendingbc;
    private ArrayList<Treasure> VisibleTreasure = new ArrayList();
    private ArrayList<Treasure> HiddenTreasure = new ArrayList();

    
    public String toString() {
        return name + "\n    Nivel: " + level ;
    }
    public Player(String name, int level) {
        this.name = name;
        this.level = level;
    }
    
    public Player(String name){
        this.name = name;
    }
    /*Devuelve la vida al jugador, modificando el atributo correspondiente.*/
    private void bringToLife(){
        dead = false;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public BadConsequence getPendingbc() {
        return pendingbc;
    }

    public ArrayList<Treasure> getHiddenTreasures(){
        return HiddenTreasure;
    }
    public ArrayList<Treasure> getVisibleTreasures(){
        return VisibleTreasure;
    }
    
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPendingbc(BadConsequence pendingbc) {
        this.pendingbc = pendingbc;
    }

    public void setVisibleTreasure(ArrayList<Treasure> visibleTreasure) {
        this.VisibleTreasure = visibleTreasure;
    }

    public void setHiddenTreasure(ArrayList<Treasure> hiddenTreasure) {
        this.HiddenTreasure = hiddenTreasure;
    }
    
    /*Devuelve el nivel de combate del jugador, que viene dado por su nivel más 
    los bonus que le proporcionan los tesoros que tenga equipados, según las 
    reglas del juego.*/
    
    private int getCombatLevel(){
        int totalLevel = getLevels();
        for(int j = 0; j < VisibleTreasure.size();j++){
            if(VisibleTreasure.get(j).getType() == TreasureKind.necklace){
                totalLevel += VisibleTreasure.get(j).getMaxBonus();
            }else{
                totalLevel += VisibleTreasure.get(j).getMinBonus();
            }
        }
        return totalLevel;
    }
    /*Incrementa el nivel del jugador en i niveles, teniendo en cuenta las reglas del juego.*/
    private void incrementLevels(int i){
        level += i;
    }
    /*Decrementa el nivel del jugador en i niveles, teniendo en cuenta las reglas de juego.*/
    private void decrementLevels(int i){
        level -= i;
        
        if(level <= 1){
            level = 1;
        }
    }
    /*Asigna el mal rollo al jugador, dándole valor a su atributo pendingBadConsequence.*/
    private void setPendingBadConsequence(BadConsequence bc){
        pendingbc = bc;
    }
    /*Cambia el estado de jugador a muerto si no tiene ni tesoros visibles ni ocultos, modificando el correspondiente atributo.*/
    private void dieIfNoTreasures(){
        if(VisibleTreasure.isEmpty() && HiddenTreasure.isEmpty())
            dead = true;
    }
    /*Si el jugador tiene equipado el tesoro tipo NECKLACE, se lo pasa al CardDealer y 
    lo elimina de sus tesoros visibles.*/
    private void discardNecklaceIfVisible(){
        for(Treasure t : this.VisibleTreasure){
            if(t.getType() == TreasureKind.necklace){
                CardDealer cd = CardDealer.getInstance();
                this.VisibleTreasure.remove(t);
            }
        }
    }
    
    /* Cuando el jugador muere en un combate, esta operación es la encargada de dejarlo sin
    tesoros, ponerle el nivel a 1 e indicar que está muerto, en el atributo correspondiente.
    */
    
    private void die(){
    
        this.setLevel(1);
        CardDealer dealer= CardDealer.getInstance();
        //iterador sobre la coleccion visible treasures
        for(Treasure t : VisibleTreasure){
            dealer.giveTreasureBack(t);
        }
        
        this.VisibleTreasure.clear();//5
        //iterador sobre la coleccion hidden treasures
        for(Treasure t : HiddenTreasure){
            dealer.giveTreasureBack(t);
        }
        this.HiddenTreasure.clear();
        this.dieIfNoTreasures();//9
        
        
    }
    /*Calcula y devuelve los niveles que puede comprar el jugador con la lista t de tesoros.*/
    //devuelve un float tal y como dice en la práctica
    private float computeGoldCoinsValue(ArrayList<Treasure> treasure){
        int levels;
        int coins = 0;
        for(Treasure t : treasure){
            coins += t.getGoldCoins();
        }
        levels = coins;
        return levels;
    }
    /*Devuelve true si con los niveles que compra no gana la partida y false en caso contrario.*/
    private boolean canIBuyLevels(int i){
        if(level + i >= 10){
            return false;
        }else{
            return true;
        }
    }
    
    /* Cuando el jugador gana el combate, esta operación es la encargada de aplicar
    el buen rollo al jugador, sumando los niveles correspondiente y robando los tesoros 
    indicados en el buen rollo del monstruo. */
    
    private void applyPrize(Monster currentMonster){
        
        int nLevels=currentMonster.getLevelsGained();
        this.incrementLevels(nLevels);
        int nTreasures=currentMonster.getTreasuresGained();
        
        if(nTreasures>0){
            CardDealer dealer=CardDealer.getInstance();
            
            for(int i = 0;i < nTreasures; i++){
                
                HiddenTreasure.add(dealer.nextTreasure());
            }
            
        }
        
    }
    
    /* Cuando el jugador ha perdido el combate, no ha podido huir y no muere, hay que 
    almacenar el mal rollo que le impone el monstruo con el que combatió. Para ello, 
    decrementa sus niveles según indica el mal rollo y guarda una copia de un objeto 
    badConsequence ajustada a los tesoros que puede perder según indique el mal rollo 
    del monstruo y de los tesoros que disponga el jugador, la operación encargada de 
    hacer esto es adjustToFitTreasureList de la clase badConsequence, este es el mal 
    rollo pendiente (pendingbadConsequence) que el jugador almacenará y que deberá 
    cumplir descartándose de esos tesoros antes de que pueda pasar al siguiente turno. */
    
    private void applyBadConsequence(BadConsequence bad){
        
        int nLevels=this.getLevels();
        this.decrementLevels(nLevels);
        this.pendingbc=bad.adjustToFitTreasureList(this.VisibleTreasure, this.HiddenTreasure);
        this.setPendingBadConsequence(this.pendingbc);
        
    }
    /*Comprueba si el tesoro (t) se puede pasar de oculto a visible, según las reglas del juego*/
    private boolean canMakeTreasureVisible(Treasure treasure){
        int handsInUse = 0;
        int repeat = 0;
        boolean canI;
        //Si hay 4 o mas tesoros visibles, no se puede hacer visible el tesoro oculto
        if(VisibleTreasure.size() >= 4)canI = false;
        
        //Revisamos los tesoros visibles que tenemos para evitar tener mas tesoros de mano de la cuenta
        for(Treasure t : getVisibleTreasures()){
            if(t.getType() == TreasureKind.oneHand){
                handsInUse++;
            }else if(t.getType() == TreasureKind.bothHand){
                handsInUse = 2;
            }
            
            for(Treasure th : getHiddenTreasures()){
                if(th.getType() == t.getType()){
                    repeat = 1;
                }else{
                    repeat = 0;
                }
            }
        }
        //Si tenemos las manos ocupadas, evitamos "visibilizar" mas armaduras de manos(ya sea de una o de las 2)
        if(handsInUse >= 2 && treasure.getType() == TreasureKind.oneHand || handsInUse >= 2 && treasure.getType() == TreasureKind.bothHand || repeat == 1){
            canI = false;
        } else{
            canI = true;
        }
        
        return canI;
    }
    /*Devuelve el número de tesoros visibles que tiene del tipo tKind.*/
    private int howManyVisibleTreasures(TreasureKind tKind){
        return VisibleTreasure.size();
    }
    /*Devuelve true si el jugador está muerto, false en caso contrario.*/
    public boolean isDead(){
        if(this.dead==true){
            return true;
        }else{
            return false;
        }
    }
    
    
    public CombatResult combat(Monster m){
        CombatResult result;
        //El nivel del jugador es su propio nivel mas el bonus!!
        int myLevel= this.getCombatLevel();//1.1.1
        int monsterLevel= m.getCombatLevel();//1.1.2
        
        
        if(myLevel>monsterLevel){
            this.applyPrize(m);
            if(this.level>=10){
                result=CombatResult.WinAndWinGame;
            }else{
                result=CombatResult.Win;
            }
        }else{
            //obtener la unica instancia de dice
            Dice dice=Dice.getInstance();
            
            int escape=dice.nextNumber();//1.1.4
            if(escape<5){
                boolean amIDead=m.kills();//1.1.5
                if(amIDead==true){
                    this.die();//1.1.6
                    result=CombatResult.LoseAndDie;
                }else{
                    BadConsequence bad=m.getBadConsequence();
                    result=CombatResult.Lose;
                    this.applyBadConsequence(bad);
                }
                       
            }else{
                result=CombatResult.LoseAndEscape;
            }
        }
        
        this.discardNecklaceIfVisible();
        
        return result;//1.1.10
    }
    
    public void makeTreasureVisible(Treasure t){
        boolean canI=this.canMakeTreasureVisible(t);//1.2.1
        if(canI){
            this.VisibleTreasure.add(t);//1.2.2
            this.HiddenTreasure.remove(t);//1.2.3
        }
    }
    
    public void discardVisibleTreasure(Treasure t){
        this.VisibleTreasure.remove(t);
        if((pendingbc!=null)&&(!pendingbc.isEmpty())){
            pendingbc.substractVisibleTreasure(t);//1.2.2
        }
        this.dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        this.HiddenTreasure.remove(t);
        if((pendingbc!=null)&&(!pendingbc.isEmpty())){
            pendingbc.substractHiddenTreasure(t);
        }
        this.dieIfNoTreasures();
    }
    
    /*Esta operación permite comprar niveles antes de combatir con el monstruo actual. 
    Para ello, a partir de las listas de tesoros (pueden ser tanto ocultos como visibles) 
    se calculan los niveles que puede subir el jugador en función del número de piezas de 
    oro que sumen. Si al jugador le está permitido comprar la cantidad de niveles resultantes 
    (no se puede comprar niveles si con ello ganas el juego), entonces se produce el mencionado 
    incremento. Independientemente de si se ha podido comprar niveles o no, los tesoros empleados 
    para ello pasan al mazo de descartes.*/
    
    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden){
        
        float levelsMayBought=0;
        levelsMayBought=this.computeGoldCoinsValue(visible);//1.1.1
        levelsMayBought+=this.computeGoldCoinsValue(hidden);//1.1.2
        
        //lo convierto a int. preguntar por esto
        //System.out.println(levelsMayBought);
        int levels=(int) (levelsMayBought/1000);
        
        boolean canI=this.canIBuyLevels(levels);//1.1.3
        
        if(canI){
            this.incrementLevels(levels);//1.1.4
        }
        
        this.VisibleTreasure.removeAll(visible);//1.1.5
        this.HiddenTreasure.removeAll(hidden);//1.1.6
        CardDealer dealer=CardDealer.getInstance();//1.1.7
        //iterador sobre la coleccion visible
        for(Treasure t : visible){
            dealer.giveTreasureBack(t);
        }
        //iterador sobre la coleccion hidden
        for(Treasure h : hidden){
            dealer.giveTreasureBack(h);
        }
        
        return false;
    }
    //Devuelve true cuando el jugador no tiene que cumplir con ningún mal rollo 
    //que cumplir (pendingBadConsequence.isEmpty() == true) y no tiene más de 4 tesoros 
    //ocultos y false en caso contrario.
    public boolean validState(){
        if(this.pendingbc == null && this.HiddenTreasure.size() <= 4|| this.VisibleTreasure.size() == 0 || this.HiddenTreasure.size() == 0){
            return true;
        }else{
            return false;
        }
       //return this.pendingbc==null || (this.pendingbc.isEmpty() && this.HiddenTreasure.size() <=4);
        
    }
    
    /*Cuando un jugador está en su primer turno o se ha quedado sin tesoros 
    ocultos o visibles, hay que proporcionarle nuevos tesoros para que pueda 
    seguir jugando. El número de tesoros que se les proporciona viene dado por 
    el valor que saque al tirar el dado:
    Si(dado == 1) roba un tesoro.
    Si(1 < dado< 6) roba dos tesoros. 
    Si (dado == 6) roba tres tesoros.*/
    
    public void initTreasures(){
    
        CardDealer dealer=CardDealer.getInstance();
        Dice dice=Dice.getInstance();
        this.bringToLife();
        Treasure treasure=dealer.nextTreasure();
        this.HiddenTreasure.add(treasure);
        int number=dice.nextNumber();
        
        if(number>1){
            treasure=dealer.nextTreasure();
            this.HiddenTreasure.add(treasure);
        }
        
        if(number==6){
            treasure=dealer.nextTreasure();
            this.HiddenTreasure.add(treasure);
        }
    }
    /*Devuelve true cuando el jugador tiene algún tesoro visible y false en caso contrario.*/
    public boolean hasVisibleTreasures(){
        if(VisibleTreasure.isEmpty())return false;
        else return true;
    }
    /*Devuelve los niveles del jugador.*/
    public int getLevels(){
        return level;
    }
   
    
}
