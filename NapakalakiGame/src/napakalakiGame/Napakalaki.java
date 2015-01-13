package napakalakiGame;

import java.util.ArrayList;
import java.util.Random;

public class Napakalaki {
    //establecemos la clase como singleton
    public static final Napakalaki instance = new Napakalaki();
    private Player currentPlayer ;
    private ArrayList<Player> players = new ArrayList();
    private Monster currentMonster;
    private CardDealer dealer = CardDealer.getInstance();
    
    private Napakalaki(){}
    //Métodos
    //Inicializa el array de jugadores que contiene Napakalaki, creando tantos 
    //jugadores como elementos haya en names, que es el array de String que 
    //contiene el nombre de los jugadores.
    private void initPlayers( ArrayList<String> names){
        
        //Recorremos los nombres pasados y creamos tantos jugadores como nombres
        for (String s : names) {

            players.add(new Player(s));

        }
    }
    
    /*Decide qué jugador es el siguiente en jugar. Se pueden dar dos posibilidades para calcular
    el índice que ocupa dicho jugador en la lista de jugadores:
    Que sea el primero en jugar, en este caso hay que generar un número aleatorio entre 0 y el numero de jugadores menos 1, este número indicará el índice que ocupa en la lista de jugadores el jugador que comenzará la partida.
    Que no sea el primero en jugar, en este caso el índice es el del jugador que se encuentra en la siguiente posición al jugador actual. Hay que tener en cuenta que si el jugador actual está en la última posición, el siguiente será el que está en la primera posición.
    Una vez calculado el índice, devuelve el jugador que ocupa esa posición.*/
    
    private Player nextPlayer(){
        int index;
        Player nextPlayer;
        //obtenemos el numero total de jugadores
        int numberOfPlayers = players.size();
        //Si no está definido el jugador actual es porque es la primera vez
        if(currentPlayer == null){
            //creamos un objeto ramdon
            Random r = new Random();
            //Obtenemos un numero aleatorio con tope el índice maximo del 
            //numero de jugadores
            index = r.nextInt(numberOfPlayers);
            //le paso el jugador siguiente
            return this.players.get(index);
        }else{
            int currentPlayerNumber = players.indexOf(currentPlayer);
            if(currentPlayerNumber == numberOfPlayers - 1){
                index = 0;
            }else{
                index = currentPlayerNumber + 1;
            }
            //le paso el jugador siguiente
            return this.players.get(index);
        }
        //return currentPlayer;
    }
    //Método que comprueba si el jugador activo (currentPlayer) cumple con las 
    //reglas del juego para poder terminar su turno. Devuelve false si el juga-
    //dor activo no puede pasar de turno y true en caso contrario, para ello 
    //usa el método de Player: validState()

    private boolean nextTurnAllowed(){
        
         boolean stateOK;
        
        if(this.currentPlayer==null){
            stateOK=true;//la primera vez allowed esta sin asignar
        }else{
            stateOK=this.currentPlayer.validState();//1.1.1
        }
        return stateOK;
    }
    
    public static Napakalaki getInstance(){
        return instance;
    }
    
    /*Operación responsabilidad de la única instancia de Napakalaki, la cual pasa
    el control al jugador actual (currentPlayer) para que lleve a cabo el combate 
    con el monstruo que le ha tocado (currentMonster). El método correspondiente
    de la clase Player para llevar a cabo dicha responsabilidad es 
    combat(currentMonster:Monster): CombatResult, cuyo algoritmo general 
    (también reflejado en el diagrama y responsabilidad de Player) es :*/
    
    public CombatResult developCombat(){
        
        CombatResult combat= currentPlayer.combat(currentMonster);
        
        this.dealer.giveMonsterBack(currentMonster);//1.2
        
        return combat;
    }
    
    /*Operación encargada de eliminar los tesoros visibles indicados de la lista 
    de tesoros visibles del jugador. Al eliminar esos tesoros, si el jugador tiene 
    un mal rollo pendiente, se indica a éste dicho descarte para su posible actualización.*/
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        //iterador sobre la coleccion treasure
        for(Treasure t: treasures){
          
            currentPlayer.discardVisibleTreasure(t); //1.2
            dealer.giveTreasureBack(t); // 1.3
        
        }
        
    }
    /*Análoga a la operación anterior aplicada a tesoros ocultos. Realizar el 
    correspondiente diagrama de secuencia.*/
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
    
        for(Treasure t: treasures){
            currentPlayer.discardHiddenTreasure(t);
            dealer.giveTreasureBack(t);
        }
        
    }
    
    /* Operación en la que el jugador pasa tesoros ocultos a visibles, siempre que 
    pueda hacerlo según las reglas del juego, para ello llama al método, para ello 
    desde Player se ejecuta el 
    método: canMakeTreasureVisible(treasures:Treasure ):boolean */
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
    
        for(Treasure t : treasures){
            this.currentPlayer.makeTreasureVisible(t);//1.2
        }
        
    }
    
    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden){
        boolean canI = this.currentPlayer.buyLevels(visible, hidden);
        return canI;
    }
    
    /*Se encarga de solicitar a CardDealer la inicialización de los mazos de cartas 
    de Tesoros y de Monstruos, de inicializar los jugadores proporcionándoles un nombre
    y de iniciar el juego con la llamada a nextTurn() para pasar al siguiente turno
    (que en este caso será el primero).*/
    
    public void initGame(ArrayList<String> players){
        this.initPlayers(players);
        
        this.dealer.initCards();
        
        this.nextTurn();
        
        
    }
    /*Devuelve el jugador actual (currentPlayer)*/
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
    /*Devuelve el monstruo en juego (currentMonster)*/
    public Monster getCurrentMonster(){
        return currentMonster;
    }
    
    /*Esta operación usa el método nextTurnIsAllowed(), para verificar si el jugador 
    activo (currentPlayer) cumple con las reglas del juego para poder terminar su turno.
    En caso el caso que nextTurnIsAllowed() devuelva true, se actualiza el jugador activo al 
    siguiente jugador y se le solicita a CardDealer el siguiente monstruo al que se enfrentará 
    ese jugador (currentMonster).
    En caso de que el nuevo jugador activo esté muerto, por el combate en su anterior turno
    o porque es el primer turno, se inicializan sus tesoros siguiendo las reglas del juego.
    La inicialización de los tesoros se encuentra recogida en el diagrama 
    subordinado initTreasures.*/
    
    public boolean nextTurn(){
        
        boolean stateOk=nextTurnAllowed(); //1.1
        
        if(stateOk){
         
            this.currentMonster=dealer.nextMonster();  //1.2
            this.currentPlayer=nextPlayer();  //1.3
            boolean dead=currentPlayer.isDead(); //1.4
            if(dead){
                currentPlayer.initTreasures(); //1.5
            }
            
        }else{
        
            this.currentMonster=dealer.nextMonster();
        
        }
            
        return stateOk;//1.6
    }
    
    /*Devuelve true si result tiene el valor WinAndWinGame del enumerado CombatResult, en
    caso contrario devuelve false.*/
    
    public boolean endOfGame(CombatResult result){
        if(result == CombatResult.WinAndWinGame){
            return true;
        }else{
            return false;
        }
    }
    
}   
