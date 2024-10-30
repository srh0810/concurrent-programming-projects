import javax.swing.*;
import java.awt.*;




public class Grille extends JPanel{

    private JPanel[][] cases;
    private CardLayout[][] card_cases;
    private LogiqueDuJeu jeu;
    private Fenetre fenetre;

    public Grille(Fenetre f, LogiqueDuJeu jeu){
        this.jeu = jeu;
        int[][] tableau_jeu = jeu.get_grille();
        this.fenetre = f;

        cases = new JPanel[tableau_jeu.length][tableau_jeu[0].length];
        card_cases = new CardLayout[tableau_jeu.length][tableau_jeu[0].length];
        for (int i=0; i<tableau_jeu.length;i++){
            for (int j=0; j<tableau_jeu[0].length;j++){
                cases[i][j]= new JPanel(); //chaque case est un bouton
                card_cases[i][j] = new CardLayout(); 
    
                cases[i][j].setLayout(card_cases[i][j]);
                cases[i][j].add(new case_blanche(), "mort"); // les différents états des cases possibles
                cases[i][j].add(new case_noir(),"vivant");
    
                add(cases[i][j]);
            }
        }
    }

    public void remplir_cases(int etat, int i, int j){
        if (etat== 1) {
            card_cases[i][j].show(cases[i][j], "vivant");  // Afficher la croix
        } else {
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
            jeu.EvolutionGrille(1);
            actualiser_grille();
        }
    }

    public void jeu_infini(){
        while(true){
            passer_n_etape(1);
        }
    }
}
