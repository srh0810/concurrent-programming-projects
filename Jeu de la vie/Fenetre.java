import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
        public LogiqueDuJeu jeu;
        public Grille g1;


    public Fenetre(String title){
        super(title);
        setSize(700,700);
        setPreferredSize(new Dimension(700,700));
        setLocation(MAXIMIZED_HORIZ, MAXIMIZED_VERT);

        jeu = new LogiqueDuJeu(getHeight()/10, getWidth()/10);
        g1 = new Grille(this, jeu);
        add(g1);
    }
}
