//Jose Francisco Lopez Vilchez & David Salas Castro
/*package napakalakiGame;

import java.util.ArrayList;
import java.util.Arrays;

public class PruebaNapakalaki {

    public static void main(String[] args) {
        //Creamos el array de monstruos
        ArrayList<Monster> monstruos = new ArrayList();
        
        
        //Creamos los monstruos y los introducimos en el array
        //3 Byakhees de bonanza
        BadConsequence bConsByakhees = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0, new ArrayList(Arrays.asList(TreasureKind.armor)) ,new ArrayList(Arrays.asList(TreasureKind.armor)));
        Prize pByakhees = new Prize(2, 1);
        monstruos.add(new Monster("3 Byakhees de bonanza", 8, pByakhees, bConsByakhees));
        
        //Chibithulhu
        BadConsequence bConsChibithulhu = new BadConsequence("Embobados con el lindo primigenio te descartas de tu casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.helmet)), null);
        Prize pChibithulhu = new Prize(1, 1);
        monstruos.add(new Monster("Chibithulhu", 2, pChibithulhu, bConsChibithulhu));
        
        //El sopor de Dunwich
        BadConsequence bConsSoporD = new BadConsequence("El primordial bostezo contagioso. Pierdes el calzado visible", 0, new ArrayList(Arrays.asList(TreasureKind.shoe)), null);
        Prize pSoporD = new Prize(1, 1);
        monstruos.add(new Monster("El sopor de Dunwich", 2, pSoporD, bConsSoporD));
             
        //Angeles de la noche ibicenca
        BadConsequence bConsReyRosa = new BadConsequence("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.", 0, new ArrayList(Arrays.asList(TreasureKind.oneHand)) ,new ArrayList(Arrays.asList(TreasureKind.oneHand)));
        Prize prizeAngelesNI = new Prize(4, 1);
        monstruos.add(new Monster("Angeles de la noche ibicenca", 14, prizeAngelesNI, bConsReyRosa));
        
        //El gorron en el umbral
        BadConsequence bConsGorronU = new BadConsequence("Pierdes todos tus tesoros visibles", 0, 0, 0);
        Prize pGorronU = new Prize(1, 1);
        monstruos.add(new Monster("El gorrón en el umbral", 10, pGorronU, bConsGorronU));
        
        //HPMunchcraft
        BadConsequence bConsHPM = new BadConsequence("Pierdes la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.armor)), null);
        Prize pHPM = new Prize(2, 1);
        monstruos.add(new Monster("HPMunchcraft", 6, pHPM, bConsHPM));
        
        //Bichgooth
        BadConsequence bConsBichgooth = new BadConsequence("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, new ArrayList(Arrays.asList(TreasureKind.armor)), null);
        Prize pBichgooth = new Prize(1, 1);
        monstruos.add(new Monster("Bichgooth", 2, pBichgooth, bConsBichgooth));
        
        //El rey de rosa
        BadConsequence badConsequenceReyRosa = new BadConsequence("Pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0);
        Prize prizeReyRosa = new Prize(4, 2);
        monstruos.add(new Monster("El rey de rosa", 13, prizeReyRosa, badConsequenceReyRosa));
        
        //La que redacta en las tinieblas
        BadConsequence bConsLaQueRedacta = new BadConsequence("Toses los pulmones y pierdes 2 niveles", 2, 0, 0);
        Prize pLaQueRedacta = new Prize(1, 1);
        monstruos.add(new Monster("La que redacta en las tinieblas", 2, pLaQueRedacta, bConsLaQueRedacta));
        
        //Los hondos
        BadConsequence bConsLosHondos = new BadConsequence("Estos monstruos resultan bastante superficiales y te aburren mortalmente.Estas muerto", true);
        Prize pLosHondos = new Prize(2, 1);
        monstruos.add(new Monster("Los hondos", 8, pLosHondos, bConsLosHondos));
        
        //Semillas Cthultu
        BadConsequence bConsSemC = new BadConsequence("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2);
        Prize pSemC = new Prize(1, 1);
        monstruos.add(new Monster("Semillas Cthultu", 4, pSemC, bConsSemC));
        
        //Dameargo
        BadConsequence bConsDameargo = new BadConsequence("Te intentas escaquear. Pierdes una mano visible", 0, new ArrayList(Arrays.asList(TreasureKind.oneHand)), null);
        Prize pDameargo = new Prize(2, 1);
        monstruos.add(new Monster("Dameargo", 1, pDameargo, bConsDameargo));
        
        //Pollipólipo volante
        BadConsequence bConsPollipolipo = new BadConsequence("Da mucho asquito. Pierdes 3 niveles", 3, 0, 0);
        Prize pPollipolipo = new Prize(1, 1);
        monstruos.add(new Monster("Pollipolipo volante", 3, pPollipolipo, bConsPollipolipo));
        
        //YskhtihyssgGoth
        BadConsequence bConsYsk = new BadConsequence("No le hace gracia que pronuncien mal su nombre. Estas muerto", true);
        Prize pYsk = new Prize(3, 1);
        monstruos.add(new Monster("YskhtihyssgGoth", 12, pYsk, bConsYsk));
        
        //Familia feliz
        BadConsequence bConsFamilia = new BadConsequence("La familia te atrapa. Estas muerto", true);
        Prize pFamilia = new Prize(4, 1);
        monstruos.add(new Monster("Familia feliz", 1, pFamilia, bConsFamilia));
        
        //Roboggoth
        BadConsequence bConsRobo = new BadConsequence("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visibles", 2, 1, 0);
        Prize pRobo = new Prize(2, 1);
        monstruos.add(new Monster("Roboggoth", 8, pRobo, bConsRobo));
        
        //El espia
        BadConsequence bConsEspia = new BadConsequence("Te asusta en la noche. Pierdes un casco visible", 0, new ArrayList(Arrays.asList(TreasureKind.helmet)), null);
        Prize pEspia = new Prize(1, 1);
        monstruos.add(new Monster("El espia", 5, pEspia, bConsEspia));
        
        //El lenguas
        BadConsequence bConsLenguas = new BadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        Prize pLenguas = new Prize(1, 2);
        monstruos.add(new Monster("El lenguas", 20, pLenguas, bConsLenguas));
        
        //Bicefalo
        BadConsequence bConsBicefalo = new BadConsequence("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3, new ArrayList(Arrays.asList(TreasureKind.bothHand)), null);
        Prize pBicefalo = new Prize(1, 1);
        monstruos.add(new Monster("Bicefalo", 20, pBicefalo, bConsBicefalo));
        
        
        
        //Busqueda con todos los requisitos juntos
        for (int i = 0; i < monstruos.size(); i++) {
            if (monstruos.get(i).getCombatLevel() > 10 && monstruos.get(i).getBadConsequence().getLevel()>0 && monstruos.get(i).getPrize().getLevel()>1 && monstruos.get(i).getBadConsequence().getNVisibleTreasures()>0) {
                System.out.println("Monster-> " + monstruos.get(i) + "\nBad consequence-> " + monstruos.get(i).getBadConsequence()+ "\nPrize-> " + monstruos.get(i).getPrize());
            }
            
            
        }
    }
    
}*/
