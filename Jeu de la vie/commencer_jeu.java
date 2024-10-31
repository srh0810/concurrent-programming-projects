
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class commencer_jeu implements ActionListener{
    private Grille g1;
    private LogiqueDuJeu jeu;

    public commencer_jeu(Grille g1, LogiqueDuJeu jeu){
        super();
        this.g1 = g1;
        this.jeu = jeu;
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        g1.activer_jeu();
        g1.passer_n_etape(LogiqueDuJeu.nombre_de_tours);
    }
        
}
