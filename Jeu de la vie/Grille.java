import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Grille extends JPanel{

    private JButton[][] cases;
    private CardLayout[][] card_cases;
    private LogiqueDuJeu jeu;
    private Fenetre fenetre;
    private boolean etat_du_jeu;
    

    public Grille(Fenetre f, LogiqueDuJeu jeu){
        
        etat_du_jeu = false;
        this.jeu = jeu;
        int[][] tableau_jeu = jeu.get_grille();
        this.fenetre = f;
        setBackground(Color.GRAY);
        setLayout(new GridLayout(tableau_jeu.length,tableau_jeu[0].length,1,1));
        setSize(9*fenetre.getHeight()/10, 9*fenetre.getWidth()/10);
        setPreferredSize((new Dimension(9*fenetre.getHeight()/10, 9*fenetre.getWidth()/10)));
        System.out.println(getHeight()+ "   "+ getWidth());
        //setMaximumSize(new Dimension(9*fenetre.getHeight()/10, 9*fenetre.getWidth()/10));
        //setMinimumSize(new Dimension(9*fenetre.getHeight()/10, 9*fenetre.getWidth()/10));
        
         
        
        
        cases = new JButton[tableau_jeu.length][tableau_jeu[0].length];
        card_cases = new CardLayout[tableau_jeu.length][tableau_jeu[0].length];
        for (int i=0; i<tableau_jeu.length;i++){
            for (int j=0; j<tableau_jeu[0].length;j++){
                cases[i][j]= new JButton(); //chaque case est un bouton
                
                
                card_cases[i][j] = new CardLayout(); 
    
                cases[i][j].setLayout(card_cases[i][j]);
                cases[i][j].add(new case_blanche(Fenetre.taille_cases), "mort"); // les différents états des cases possibles
                cases[i][j].add(new case_noir(Fenetre.taille_cases),"vivant");
                
                cases[i][j].setPreferredSize(new Dimension(Fenetre.taille_cases,Fenetre.taille_cases));
                //cases[i][j].setMaximumSize(new Dimension(10,10));
                //cases[i][j].setMinimumSize(new Dimension(10,10));
                
                
                cases[i][j].addActionListener(new Changer_case(i, j, this));
                
                add(cases[i][j]);
                cases[i][j].setMargin(new Insets(0, 0, 0, 0));
                cases[i][j].setBorder(BorderFactory.createEmptyBorder());
                cases[i][j].setContentAreaFilled(true);
            }
        
        }
    }

    public void remplir_cases(int etat, int i, int j){
        if (etat== 1) {
            card_cases[i][j].show(cases[i][j], "vivant");  
        } else {
        card_cases[i][j].show(cases[i][j], "mort");  
        }
    }

    public void modifier_cases(int etat, int i, int j){
        if (etat== 1) {
            jeu.get_grille()[i][j]=1;
            card_cases[i][j].show(cases[i][j], "vivant");  
        } else {
            jeu.get_grille()[i][j]=0;
            card_cases[i][j].show(cases[i][j], "mort"); 
        }
        
    }

    public void actualiser_grille(){
        for (int i=0; i<cases.length;i++){
            for (int j=0; j<cases[0].length;j++){
                remplir_cases(jeu.get_grille()[i][j], i, j);
            }
        }
        fenetre.tour_actuel += 1;
        fenetre.nb_detours_actuel.setText("L'étape en cours est "+ fenetre.tour_actuel);
        fenetre.repaint();  
        fenetre.revalidate();  
    }

    public void passer_n_etape(int n){
        final int[] stepCounter = {0};  // Utilisation d'un tableau pour pouvoir le modifier dans l'action

        Timer timer = new Timer(100, new ActionListener() { // 100ms entre chaque étape, ajustez selon besoin
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stepCounter[0] < n) {
                    jeu.EvolutionGrille();  // Calcul de l'évolution
                    actualiser_grille();    // Mise à jour de l'affichage
                    fenetre.repaint();
                    fenetre.revalidate();
                    stepCounter[0]++;
                } else {
                    ((Timer) e.getSource()).stop();  // Arrêter le timer quand c'est fini
                }
            }
        });

        timer.start();  // Démarrer le timer
    }
        //for(int k=0;k<n;k++){
        //    jeu.EvolutionGrille();
        //    actualiser_grille();
        //    fenetre.repaint();  
        //    fenetre.revalidate(); 
        //    try {
        //        TimeUnit.MILLISECONDS.sleep(1);
        //    } catch (InterruptedException e) {
                // TODO Auto-generated catch block
        //        e.printStackTrace();
         //   }
            
        //}
    

    public void jeu_infini(){
        while(true){
            passer_n_etape(1);
        }
    }

    public void reset(){
        for (int i=0; i<cases.length;i++){
            for (int j=0; j<cases[0].length;j++){
                modifier_cases(0, i, j);
            }
        }
        fenetre.tour_actuel = 0;
        fenetre.nb_detours_actuel.setText("L'étape en cours est "+ fenetre.tour_actuel);
        fenetre.repaint();
        fenetre.revalidate();
        
    }


    public void activer_jeu(){
        etat_du_jeu = true;    
    }
    public void arreter_jeu(){
        etat_du_jeu = false;    
    }

    public boolean get_etat_du_jeu(){
        return etat_du_jeu;
    }
    public LogiqueDuJeu getJeu() {
        return jeu;
    }

    public JButton[][] getCases(){
        return cases;
    }

    
}
