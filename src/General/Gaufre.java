package General;
import javax.swing.JFrame;

public class Gaufre {
    public static void main(String[] args){
        Jeu jeu = new Jeu(new JFrame(), new GraphiqueGrille(new Grille(10,10, true)), new Historique(true));
        jeu.demarrer();
    }
}
