import javax.swing.*;
import java.awt.*;




public class Grille extends JPanel{

    private JPanel[][] cases;
    private CardLayout[][] card_cases;
    private int[][] tableau_jeu;
    private Fenetre fenetre;

    public Grille(Fenetre f, LogiqueDuJeu jeu){
        this.tableau_jeu = jeu.get_grille();
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
        for (int i=0; i<tableau_jeu.length;i++){
            for (int j=0; j<tableau_jeu[0].length;j++){
                remplir_cases(tableau_jeu[i][j], i, j);
            }
        }
    }
}
