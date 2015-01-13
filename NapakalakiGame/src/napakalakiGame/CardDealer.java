
package napakalakiGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDealer {
    //establecemos la clase como singleton
    private static CardDealer instance = null;
    private ArrayList<Treasure> unusedTreasures=new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Monster> unusedMonsters=new ArrayList();
    private ArrayList<Monster> usedMonsters = new ArrayList();
    
    private CardDealer(){}
    
    /*Inicializa en mazo de cartas de Tesoros (unusedTreasures) con todas las cartas
    de tesoros proporcionadas en el pdf de cartas de tesoros.*/
    
    private void initTreasureCardDeck(){
        
        //Introducimos todos los tesoros
        unusedTreasures.add(new Treasure("¡Śı mi amo!", 0, 4, 7, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Botas de investigación", 600, 3, 4, TreasureKind.shoe));
        unusedTreasures.add(new Treasure("Capucha de Cthulhu", 500, 3, 5, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("A prueba de babas", 400, 2, 5, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Botas de lluvia ácida", 800, 1, 1, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Casco minero", 400, 2, 4, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Ametralladora Thompson", 600, 4, 8, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Camiseta de la UGR", 100, 1, 7, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Fez alópodo", 700, 3, 5, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Hacha prehistórica", 500, 2, 5, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Gaita", 500, 4, 5, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Insecticida", 300, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Escopeta de 3 cañones", 700, 4, 6, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Garabato Mistico", 300, 2, 2, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("La fuerza de Mr. T", 1000, 0, 0, TreasureKind.necklace));
        unusedTreasures.add(new Treasure("La rebeca metálica", 400, 2, 3, TreasureKind.armor));
        unusedTreasures.add(new Treasure("Mazo de los antiguos", 200, 3, 4, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necro-playboycon", 300, 3, 5, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Lanzallamas", 800, 4, 8, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Necro-comicón", 100, 1, 1, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necronomicón", 800, 5, 7, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Linterna a 2 manos", 400, 3, 6, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Necro-gnomicón", 200, 2, 4, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.oneHand));
        unusedTreasures.add(new Treasure("Tentácula de pega", 200, 0, 1, TreasureKind.helmet));
        unusedTreasures.add(new Treasure("Zapato deja-amigos", 500, 0, 1, TreasureKind.shoe));
        unusedTreasures.add(new Treasure("Shogulador", 600, 1, 1, TreasureKind.bothHand));
        unusedTreasures.add(new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.oneHand));    
    }
    
    /*Inicializa el mazo de cartas de monstruos (unusedMonsters), con todas las cartas de 
    monstruos proporcionadas en el pdf de cartas de monstruos. Se recomienda reutilizar el
    código desarrollado en la primera práctica para construir las cartas de monstruos.*/
    
    private void initMonsterCardDeck(){
       // unusedMonsters = new ArrayList();
        
        //Aniadimos los monstruos
        
        //3 Byakhees de bonanza
        BadConsequence bConsByakhees = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.armor)) ,new ArrayList(Arrays.asList(TreasureKind.armor)));
        Prize pByakhees = new Prize(2, 1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, pByakhees, bConsByakhees));
        
        //Chibithulhu
        BadConsequence bConsChibithulhu = new BadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.helmet)), new ArrayList());
        Prize pChibithulhu = new Prize(1, 1);
        unusedMonsters.add(new Monster("Chibithulhu", 2, pChibithulhu, bConsChibithulhu));
        
        //El sopor de Dunwich
        BadConsequence bConsSoporD = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, new ArrayList(Arrays.asList(TreasureKind.shoe)), new ArrayList());
        Prize pSoporD = new Prize(1, 1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, pSoporD, bConsSoporD));
             
        //Angeles de la noche ibicenca
        BadConsequence bConsReyRosa = new BadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.", 0, new ArrayList(Arrays.asList(TreasureKind.oneHand)) ,new ArrayList(Arrays.asList(TreasureKind.oneHand)));
        Prize prizeAngelesNI = new Prize(4, 1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 14, prizeAngelesNI, bConsReyRosa));
        
        //El gorron en el umbral
        BadConsequence bConsGorronU = new BadConsequence("Pierdes todos tus tesoros visibles", 0, 0, 0);
        Prize pGorronU = new Prize(1, 1);
        unusedMonsters.add(new Monster("El gorrón en el umbral", 10, pGorronU, bConsGorronU));
        
        //HPMunchcraft
        BadConsequence bConsHPM = new BadConsequence("Pierdes la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.armor)), new ArrayList());
        Prize pHPM = new Prize(2, 1);
        unusedMonsters.add(new Monster("HPMunchcraft", 6, pHPM, bConsHPM));
        
        //Bichgooth
        BadConsequence bConsBichgooth = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.armor)), new ArrayList());
        Prize pBichgooth = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bichgooth", 2, pBichgooth, bConsBichgooth));
        
        //El rey de rosa
        BadConsequence badConsequenceReyRosa = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        Prize prizeReyRosa = new Prize(4, 2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, prizeReyRosa, badConsequenceReyRosa));
        
        //La que redacta en las tinieblas
        BadConsequence bConsLaQueRedacta = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        Prize pLaQueRedacta = new Prize(1, 1);
        unusedMonsters.add(new Monster("La que redacta en las tinieblas", 2, pLaQueRedacta, bConsLaQueRedacta));
        
        //Los hondos
        BadConsequence bConsLosHondos = new BadConsequence("Estos resultan bastante superficiales y te aburren mortalmente.Estas muerto", true);
        Prize pLosHondos = new Prize(2, 1);
        unusedMonsters.add(new Monster("Los hondos", 8, pLosHondos, bConsLosHondos));
        
        //Semillas Cthultu
        BadConsequence bConsSemC = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        Prize pSemC = new Prize(1, 1);
        unusedMonsters.add(new Monster("Semillas Cthultu", 4, pSemC, bConsSemC));
        
        //Dameargo
        BadConsequence bConsDameargo = new BadConsequence("Te intentas escaquear. Pierdes una mano visible", 0, new ArrayList(Arrays.asList(TreasureKind.oneHand)), new ArrayList());
        Prize pDameargo = new Prize(2, 1);
        unusedMonsters.add(new Monster("Dameargo", 1, pDameargo, bConsDameargo));
        
        //Pollipólipo volante
        BadConsequence bConsPollipolipo = new BadConsequence("Da mucho asquito. Pierdes 3 niveles", 3, 0, 0);
        Prize pPollipolipo = new Prize(1, 1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, pPollipolipo, bConsPollipolipo));
        
        //YskhtihyssgGoth
        BadConsequence bConsYsk = new BadConsequence("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        Prize pYsk = new Prize(3, 1);
        unusedMonsters.add(new Monster("YskhtihyssgGoth", 12, pYsk, bConsYsk));
        
        //Familia feliz
        BadConsequence bConsFamilia = new BadConsequence("La familia te atrapa. Estas muerto", true);
        Prize pFamilia = new Prize(4, 1);
        unusedMonsters.add(new Monster("Familia feliz", 1, pFamilia, bConsFamilia));
        
        //Roboggoth
        BadConsequence bConsRobo = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visibles", 2, 1, 0);
        Prize pRobo = new Prize(2, 1);
        unusedMonsters.add(new Monster("Roboggoth", 8, pRobo, bConsRobo));
        
        //El espia
        BadConsequence bConsEspia = new BadConsequence("Te asusta en la noche. Pierdes un casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.helmet)), new ArrayList());
        Prize pEspia = new Prize(1, 1);
        unusedMonsters.add(new Monster("El espia", 5, pEspia, bConsEspia));
        
        //El lenguas
        BadConsequence bConsLenguas = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        Prize pLenguas = new Prize(1, 2);
        unusedMonsters.add(new Monster("El lenguas", 20, pLenguas, bConsLenguas));
        
        //Bicefalo
        BadConsequence bConsBicefalo = new BadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles", 3, new ArrayList(Arrays.asList(TreasureKind.bothHand)), new ArrayList());
        Prize pBicefalo = new Prize(1, 1);
        unusedMonsters.add(new Monster("Bicefalo", 20, pBicefalo, bConsBicefalo));
        
    }
    /*Baraja el mazo de cartas de tesoros unusedTreasures.*/
    private void shuffleTreasures(){
        Collections.shuffle(unusedTreasures);
    }
    /*Baraja el mazo de cartas de monstruos unusedMonsters.*/
    private void shuffleMonsters(){
        Collections.shuffle(unusedMonsters);
    }
    
    public static CardDealer getInstance(){
        if(instance == null){
            instance = new CardDealer();
        }
        return instance;
    }
    /*Devuelve el siguiente tesoro que hay en el mazo de tesoros (unusedTreasures)
    y lo elimina de él. Si el mazo está vacío, pasa el mazo de descartes (usedTreasures)
    al mazo de tesoros y lo baraja, dejando el mazo de descartes vacío.*/
    public Treasure nextTreasure(){
        shuffleTreasures();
        if(unusedTreasures.isEmpty()){
            for(Treasure t: usedTreasures){
                unusedTreasures.add(t);
            }
            shuffleTreasures();
            usedTreasures.clear();
        }
        Treasure t = unusedTreasures.get(0);
        usedTreasures.add(t);
        unusedTreasures.remove(t);
        return t;
    }
    /*Igual que la anterior pero con el mazo de monstruos.*/
    public Monster nextMonster(){
        shuffleMonsters();
        if(unusedMonsters.isEmpty()){
            for(Monster m : usedMonsters){
                unusedMonsters.add(m);
            }
            
            shuffleMonsters();
            usedMonsters.clear();
        }
        Monster m = unusedMonsters.get(0);
        usedMonsters.add(m);
        unusedMonsters.remove(m);
        return m;
    }
    /*Introduce en el mazo de descartes de tesoros (usedTreasures) el tesoro t.*/
    public void giveTreasureBack( Treasure t){
        usedTreasures.add(t);
    }
    /*Introduce en el mazo de descartes de monstruos (usedMonsters) al monstruo m.*/
    public void giveMonsterBack(Monster m){
        usedMonsters.add(m);
    }
    
    public void initCards(){
        this.initMonsterCardDeck();//1.2.1
        this.initTreasureCardDeck();//1.2.2
    }
}
