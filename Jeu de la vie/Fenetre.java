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
        setContentPane(panelA);
        panelA.setLayout(new FlowLayout());

        JButton nv_jeu = new JButton("Nouveau jeu");
        
       

        jeu = new LogiqueDuJeu(9*getHeight()/100, 9*getWidth()/100);
        g1 = new Grille(this, jeu);
        Nouv_partie nvl_partie = new Nouv_partie(g1);
        nv_jeu.addActionListener(nvl_partie);

        panelA.add(nv_jeu);


        panelA.add(g1);

        pack();
    }
}
