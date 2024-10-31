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
            cases[i][j].repaint();  // Forcer le redessin de la case
            cases[i][j].revalidate();
        } else {
        card_cases[i][j].show(cases[i][j], "mort");  
        cases[i][j].repaint();  // Forcer le redessin de la case
        cases[i][j].revalidate(); // Réinitialiser la case
        }
    }

    public void modifier_cases(int etat, int i, int j){
        if (etat== 1) {
            jeu.get_grille()[i][j]=1;
            card_cases[i][j].show(cases[i][j], "vivant");  
            cases[i][j].repaint();  // Forcer le redessin de la case
            cases[i][j].revalidate();
        } else {
            jeu.get_grille()[i][j]=0;
            card_cases[i][j].show(cases[i][j], "mort"); 
            cases[i][j].repaint();  // Forcer le redessin de la case
            cases[i][j].revalidate();  // Réinitialiser la case
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

    public JButton[][] getCases(){
        return cases;
    }

    
}
