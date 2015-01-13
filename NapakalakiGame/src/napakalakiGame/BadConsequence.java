package napakalakiGame;

import java.util.ArrayList;

//Variables
public class BadConsequence {
    private String text;
    private int level;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();

    //Constructores
    public BadConsequence(String text, int level, int nVisibleTreasures, int nHiddenTreasures) {
        this.text = text;
        this.level = level;
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
    }

    public BadConsequence(String text, boolean death) {
        this.text = text;
        this.death = death;
    }

    public BadConsequence(String text, int level, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        this.text = text;
        this.level = level;
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;
    }

    /*Devuelve true cuando el mal rollo que tiene que cumplir el jugador está vacío, 
    eso significa que el conjunto de atributos del mal rollo indican que no hay 
    mal rollo que cumplir, plantéate qué valores deberán tener.*/
    
    public boolean isEmpty(){
        boolean empty = false;
        
        if(level == 0 && death == false && nHiddenTreasures == 0 && nVisibleTreasures == 0 && specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty()){
            empty = true;
        }else{
            empty = false;
        }
        
        return empty;
    }

    public int getLevel() {
        return level;
    }
    
    public int getNVisibleTreasures() {
        return nVisibleTreasures;
    }
    
    public int getNHiddenTreasures() {
        return nHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }
    /*Actualiza el mal rollo que tiene que cumplir el jugador, en función del tipo de tesoro de t y
    del tipo de mal rollo que tenga que cumplir el jugador.*/
    public void substractVisibleTreasure(Treasure t){
        this.specificVisibleTreasures.remove(t.getType());
    }
    /*Actualiza el mal rollo que tiene que cumplir el jugador, en función del tipo de tesoro de t y
del tipo de mal rollo que tenga que cumplir el jugador.*/
    public void substractHiddenTreasure(Treasure t){
        this.specificHiddenTreasures.remove(t.getType());
    }
    
    /* Recibe como parámetros los tesoros visibles y ocultos de los que dispone el jugador
    y devuelve un nuevo objeto mal rollo que contiene una lista de tipos tesoros visibles y
    ocultos de los que el jugador debe deshacerse. El objeto de mal rollo devuelto por 
    adjustToFitTreasureList solo contendrá tipos de tesoros y en una cantidad adecuada a 
    los que el jugador puede llegar a deshacerse en función de los que dispone. */
    
    public BadConsequence adjustToFitTreasureList(ArrayList <Treasure> v, ArrayList<Treasure> h){
        
        ArrayList<TreasureKind> tVisible = new ArrayList();
        ArrayList<TreasureKind> tHidden = new ArrayList();
        
        //Recorremos los tesoros
        for (Treasure t: v) {
            if(this.getSpecificVisibleTreasures()==null){
                tVisible.add(t.getType());
            }
            //Si no contiene el TreasureKind lo agregamos
            if (this.getSpecificVisibleTreasures().contains(t.getType())) {
                tVisible.add(t.getType());
            }
        }
        
        //Recorremos los tesoros
        for (Treasure t: h) {
            if(this.getSpecificHiddenTreasures()==null){
               
            }
            //Si no contiene el TreasureKind lo agregamos
            if (this.getSpecificHiddenTreasures().contains(t.getType())) {
                tHidden.add(t.getType());
            }
        }

        BadConsequence b = new BadConsequence(this.text, 0, tVisible, tHidden);
        int visible=this.getNVisibleTreasures();
        int hidden=this.getNHiddenTreasures();
        b.nHiddenTreasures=hidden;
        b.nVisibleTreasures=visible;
        
        //falta algo aqui sobre lo anterior
        return b;
    }
    /*Devuelve true si es un mal rollo es muerte, false en caso contrario.*/
    public boolean myBadConsequenceIsDeath(){
        if(death == true){
            return true;
        }else{
            return false;
        }
    }
    
    //EXAMEN
    public BadConsequence substractTreasures(ArrayList <Treasure> tVisibles, ArrayList<Treasure> tHidden){
        BadConsequence b;
        for(int i = 0; i < tVisibles.size(); i++){
            tVisibles.remove(i);
        }
        
        for(int j = 0; j < tHidden.size(); j++){
            tHidden.remove(j);
        }
        b = new BadConsequence(text, level, tVisibles.size(), tHidden.size());
        return b;
    }
    
    public String getText() {
        return text;
    }
//
//    //Mostramos esto:
//    public String toString(){
//        return "Text = " + text+ ", Levels = " + Integer.toString(level) + ", Visible treasures = " + nVisibleTreasures
//                + ", Hidden treasures = " + nHiddenTreasures;
//    }
   }
