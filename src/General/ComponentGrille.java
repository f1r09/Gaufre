package General;


import IA.IAAleatoire;
import IA.IAMoyenne;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class ComponentGrille extends JComponent {
    Graphics2D dessin;
    Grille grille;
    int tailleCase;
    IAMoyenne IA;



    public ComponentGrille(Grille g) {
        grille = g;
        IA = new IAMoyenne(grille);
    }


    public Grille getGrille(){
    	return grille;
    }
    
    
    public int tailleCase() {
    	return tailleCase;
    }

    public boolean jouer(int l, int c)  {
        boolean joue = false;
        if(l > grille.lignes() - 1 || c > grille.colonnes() - 1){
            return false;
        }
        if (!grille.estMangee(l, c)) {
            grille.manger(l, c);
            grille.getHistorique().ajouterCoup(new Point(l, c));
            joue = true;
        }
        repaint();
        return joue;
    }

    public void jouerAI(){
        Point p = IA.joueIAMoyenne();
        grille.manger(p.x, p.y);
        grille.getHistorique().ajouterCoup(p);
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        dessin = (Graphics2D) graphics;

        /* recuperation des dimensions de la fenetre */
        int largeurFen = getSize().width;
        int hauteurFen = getSize().height;

        /* calcul de la taille des cases */
        if (Math.min(largeurFen, hauteurFen) == largeurFen)
        	tailleCase = largeurFen / grille.colonnes();
        else
        	tailleCase = hauteurFen / grille.lignes();

        /* effacement de l'ecran */
        dessin.clearRect(0, 0, largeurFen, hauteurFen);

        /* affichage de la gaufre */
        for (int i = 0; i < grille.lignes(); i++) {
            for (int j = 0; j < grille.colonnes(); j++) {
                if (grille.estMangee(i, j))
                    dessin.setColor(new Color(0xFFFFFF));
                else
                    dessin.setColor(new Color(0xFB8701));
                dessin.fillRect(tailleCase * j, tailleCase * i, tailleCase, tailleCase);
            }
        }

        /* affichage des lignes horizontales */
        dessin.setColor(new Color(0x000000));
        for (int i = 0; i <= grille.lignes(); i++) {
            int y = i * tailleCase;
            dessin.drawLine(0, y, grille.colonnes() * tailleCase, y);
        }

        /* affichage des lignes verticales */
        for (int j = 0; j <= grille.colonnes(); j++) {
            int x = j * tailleCase;
            dessin.drawLine(x, 0, x, grille.lignes() * tailleCase);
        }

        /* affichage du poison */
        dessin.setColor(new Color(0xFF27BB6B));
        dessin.fillOval(tailleCase / 4,  tailleCase / 4, tailleCase / 2, tailleCase / 2);
    }
}