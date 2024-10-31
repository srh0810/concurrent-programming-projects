import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
        public LogiqueDuJeu jeu;
        public Grille g1;
        static final int taille_cases = 30;


    public Fenetre(String title){
        super(title);
        setSize(800,800);
        setPreferredSize(new Dimension(800,800));
        setLocation(MAXIMIZED_HORIZ, MAXIMIZED_VERT);

        Panel_principale panelA = new Panel_principale();
        setContentPane(panelA);
        panelA.setLayout(new FlowLayout());

        JButton nv_jeu = new JButton("Nouveau jeu");
        
       

        jeu = new LogiqueDuJeu(9*getHeight()/(10*taille_cases), 9*getWidth()/(10*taille_cases));
        System.out.println(" largeur "+9*getHeight()/(10*taille_cases) + " longueur " + 9*getWidth()/(10*taille_cases));
        g1 = new Grille(this, jeu);
        Nouv_partie nvl_partie = new Nouv_partie(g1);
        nv_jeu.addActionListener(nvl_partie);

        panelA.add(nv_jeu);


        panelA.add(g1);
        repaint();
        revalidate();
        pack();

       
    }
}
