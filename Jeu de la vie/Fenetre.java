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

        Panel_principale panelA = new Panel_principale();
        panelA.setLayout(new FlowLayout());

        JButton nv_jeu = new JButton("Nouveau jeu");
        
        Nouv_partie nvl_partie = new Nouv_partie(nv_jeu);
        nv_jeu.addActionListener(nvl_partie);

        panelA.add(nv_jeu);

        jeu = new LogiqueDuJeu(getHeight()/10, getWidth()/10);
        g1 = new Grille(this, jeu);


        panelA.add(g1);
    }
}
