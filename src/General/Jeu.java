package General;


import java.awt.BorderLayout;

import javax.swing.*;

import Buttons.*;


public class Jeu  extends JFrame {
	JFrame frame;
	GraphiqueGrille gg;
	
	
	public Jeu() {
    	frame = new JFrame();
    	gg = new GraphiqueGrille(new Grille(10, 10, true));
	}
	
	
	public Grille getGrille() {
		return gg.getGrille();
	}
	
	
	public GraphiqueGrille getGraphiqueGrille() {
		return gg;
	}
	
	
	public Historique getHistorique() {
		return gg.getGrille().getHistorique();
	}

	
    void demarrer(){
        frame.setTitle("Gaufre empoisonnée");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(true);
        
        gg.addMouseListener(new EcouteurSouris(gg));
        frame.add(gg);
        
        Box boxBoutons = Box.createVerticalBox();
        JButton boutonSauver =  new BoutonSauver(this);
        JButton boutonCharger =  new BoutonCharger(this);
        JButton boutonAnnuler = new BoutonAnnuler(this);
        JButton boutonRefaire = new BoutonRefaire(this);
        boxBoutons.add(boutonSauver);
        boxBoutons.add(boutonCharger);
        boxBoutons.add(boutonAnnuler);
        boxBoutons.add(boutonRefaire);
        frame.add(boxBoutons, BorderLayout.EAST);
        
        frame.setVisible(true);
    }
}
