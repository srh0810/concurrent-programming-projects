import javax.swing.*;
import java.awt.*;




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
        setSize(fenetre.getWidth()*9/10,fenetre.getHeight()*9/10);
        setPreferredSize(new Dimension(fenetre.getWidth()*9/10,fenetre.getHeight()*9/10));
        setLayout(new GridLayout(tableau_jeu.length,tableau_jeu[0].length));
        cases = new JButton[tableau_jeu.length][tableau_jeu[0].length];
        card_cases = new CardLayout[tableau_jeu.length][tableau_jeu[0].length];
        for (int i=0; i<tableau_jeu.length;i++){
            for (int j=0; j<tableau_jeu[0].length;j++){
                cases[i][j]= new JButton(); //chaque case est un bouton
                card_cases[i][j] = new CardLayout(); 
    
                cases[i][j].setLayout(card_cases[i][j]);
                cases[i][j].add(new case_blanche(fenetre.getWidth()*9/10,fenetre.getHeight()*9/10), "mort"); // les différents états des cases possibles
                cases[i][j].add(new case_noir(fenetre.getWidth()*9/10,fenetre.getHeight()*9/10),"vivant");
                cases[i][j].addActionListener(new Changer_case(i, j, this));
                add(cases[i][j]);
            }
        }
    }

    public void remplir_cases(int etat, int i, int j){
        if (etat== 1) {
            card_cases[i][j].show(cases[i][j], "vivant");  
        } else {
        card_cases[i][j].show(cases[i][j], "mort");   // Réinitialiser la case
        }
    }

    public void modifier_cases(int etat, int i, int j){
        if (etat== 1) {
            jeu.get_grille()[i][j]=1;
            card_cases[i][j].show(cases[i][j], "vivant");  
        } else {
            jeu.get_grille()[i][j]=0;
            card_cases[i][j].show(cases[i][j], "mort");   // Réinitialiser la case
        }
    }

    public void actualiser_grille(){
        for (int i=0; i<cases.length;i++){
            for (int j=0; j<cases[0].length;j++){
                remplir_cases(jeu.get_grille()[i][j], i, j);
            }
        }
    }

    public void passer_n_etape(int n){
        for(int k=0;k<n;k++){
            jeu.EvolutionGrille();
            actualiser_grille();
        }
    }

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

    
}
