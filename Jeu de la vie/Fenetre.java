import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
        public LogiqueDuJeu jeu;
        public Grille g1;
        static final int taille_cases = 10;
        public JLabel nb_detours_actuel;
        int tour_actuel =0;


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
        
        g1 = new Grille(this, jeu);

        Nouv_partie nvl_partie = new Nouv_partie(g1);
        nv_jeu.addActionListener(nvl_partie);

        JButton commencer_partie = new JButton("Commencer le jeu");
        commencer_jeu com_partie_listener = new commencer_jeu(g1,jeu);
        commencer_partie.addActionListener(com_partie_listener);



        nb_detours_actuel = new JLabel("L'étape en cours est "+ tour_actuel);



        panelA.add(nv_jeu);

        panelA.add(commencer_partie);

        panelA.add(nb_detours_actuel);


        panelA.add(g1);
        repaint();
        revalidate();
        pack();

       
    }
}
