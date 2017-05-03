
package cardgame.factory;
import cardgame.*;
import cardgame.cards.*;

public class CardFactory implements Factory{
    public CardFactory(){}
    public Card returnCard(int choice){
        //Setting a null perchè altrimenti netbeans rompe a fine switch, non ritorna mai qualcosa di nullo
        Card ritorno = null;
        switch (choice){
            case 1:
                System.out.println("Added Abduction");
                ritorno = new Abduction();
                break;
            case 2:
                System.out.println("Added AetherBarrier");
                ritorno = new AetherBarrier();
                break;
            case 3:
                System.out.println("Added AetherFlash");
                ritorno = new AetherFlash();
                break;
            case 4:
                System.out.println("Added Afflict");
                ritorno = new Afflict();
                break;
            case 5:
                System.out.println("Added AggressiveUrge");
                ritorno = new AggressiveUrge();
                break;
            case 6:
                System.out.println("Added AncestralMask");
                ritorno = new AncestralMask();
                break;
            case 7:
                /*DA MODIFICARE IL NEW, WORKAROUND MOMENTANEO FINCHE' NON VIENE CREATA LA CARTA*/
                System.out.println("Added ArgothianEnchantress");
                ritorno = new Homeopathy();
                break;
            case 8:
                System.out.println("Added AuraBlast");
                ritorno = new AuraBlast();
                break;
            case 9:
                /*DA MODIFICARE IL NEW, WORKAROUND MOMENTANEO FINCHE' NON VIENE CREATA LA CARTA*/
                System.out.println("Added BenevolentAncestror");
                ritorno = new Homeopathy();
                break;
            case 10:
                System.out.println("Added BoilingEarth");
                ritorno = new BoilingEarth();
                break;
            case 11:
                System.out.println("Added BronzeSable");
                ritorno = new BronzeSable();
                break;
            case 12:
                System.out.println("Added CalmingVerse");
                ritorno = new CalmingVerse();
                break;
            case 13:
                System.out.println("Added Cancel");
                ritorno = new Cancel();
                break;
            case 14:
                System.out.println("Added Darkness");
                ritorno = new Darkness();
                break;
            case 15:
                System.out.println("Added DayOfJudgment");
                ritorno = new DayOfJudgment();
                break;
            case 16:
                System.out.println("Added Deflection");
                ritorno = new Deflection();
                break;
            case 17:
                System.out.println("Added FalsePeace");
                ritorno = new FalsePeace();
                break;
            case 18:
                System.out.println("Added Fatigue");
                ritorno = new Fatigue();
                break;
            case 19:
                System.out.println("Added NorwoodRanger");
                ritorno = new NorwoodRanger();
                break;
            case 20:
                System.out.println("Added SavorTheMoment");
                ritorno = new SavorTheMoment();
                break;
            case 21:
                System.out.println("Added VolcanicHammer");
                ritorno = new VolcanicHammer();
                break;
            case 22:
                System.out.println("Added WorldAtWar");
                ritorno = new WorldAtWar();
                break;
            case 23:
                System.out.println("Added Reflexologist");
                ritorno = new Reflexologist();
                break;
            case 24:
                System.out.println("Added Homeopathy");
                ritorno = new Homeopathy();
                break;
            case 25:
                System.out.println("Added FriendlyEnvironment");
                ritorno = new FriendlyEnvironment();
                break;
        }
        return ritorno;
    }
}
